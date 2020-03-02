package com.pozoriche.service;

import com.pozoriche.dto.MakeDonate;
import com.pozoriche.dto.PageDto;
import com.pozoriche.exception.PageNotFoundException;
import com.pozoriche.model.*;
import com.pozoriche.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PageService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private PageRepository pageRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private BonusRepository bonusRepository;

    @Autowired
    private DonatorRepository donatorRepositoryRepository;

    public List<PageDto> getAllPages(){
        List<Page> pages = pageRepository.findAll();
        return pages.stream().map(this::mapFromPageToDto).collect(toList());
    }

    public PageDto getSinglePage(Long id){
        Page page = pageRepository.findById(id).orElseThrow(()-> new PageNotFoundException("id: "+id));
        return mapFromPageToDto(page);
    }

    public PageDto mapFromPageToDto(Page page){
        PageDto pageDto = new PageDto();
        pageDto.setId(page.getId());
        pageDto.setName(page.getName());
        pageDto.setUsername(page.getUser().getUserName());
        pageDto.setTag(page.getTag());
        pageDto.setCategories(page.getCategories());
        pageDto.setCreationDate(page.getCreationDate().toString());
        pageDto.setEndingDate(page.getEndingDate().toString());
        pageDto.setImages(page.getImages());
        pageDto.setMoney(page.getMoney());
        pageDto.setYouTubeVideo(page.getYouTubeVideo());
        pageDto.setBonusList(page.getBonusList());
        pageDto.setNeeded(page.getNeeded());
        return pageDto;
    }

    public List<Category> createCategory(List<String> categories, Page page){
        List<Category> categoryList = new ArrayList<Category>();
        for (String s : categories){
            Category category = new Category(s);
            this.categoryRepository.save(category);
            categoryList.add(category);
        }
        return categoryList;
    }

    public List<Image> createImage(List<String> images, Page page){
        List<Image> imagesList = new ArrayList<Image>();
        for (String s : images){
            Image image = new Image(s);
            this.imageRepository.save(image);
            imagesList.add(image);
        }
        return imagesList;
    }

    public List<Bonus> createBonus(List<String> bonuses, Page page){
        List<Bonus> bonusesList = new ArrayList<Bonus>();
        for (String s : bonuses){
            Bonus bonus = new Bonus(s);
            this.bonusRepository.save(bonus);
            bonusesList.add(bonus);
        }
        return bonusesList;
    }

    public Page mapFromDtoToPage(PageDto pageDto){
        Page page = new Page();
        page.setTag(pageDto.getTag());
        page.setName(pageDto.getName());
        page.setCategories(createCategory(pageDto.getCategories(), page));
        page.setCreationDate(LocalDate.now());
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(pageDto.getEndingDate(), DATEFORMATTER);
        page.setEndingDate(ld);
        page.setImages(createImage(pageDto.getImages(), page));
        page.setMoney(0L);
        page.setYouTubeVideo(pageDto.getYouTubeVideo());
        page.setBonusList(createBonus(pageDto.getBonusList(), page));
        page.setNeeded(pageDto.getNeeded());
        com.pozoriche.model.User user = userService.getUserByName(pageDto.getUsername());
        page.setUser(user);
        return page;
    }

    public void createPage(PageDto pageDto){
        com.pozoriche.model.User user = userService.getUserByName(pageDto.getUsername());
        Page page = mapFromDtoToPage(pageDto);
        user.setPage(page);
        pageRepository.save(page);
    }

    public List<PageDto> findPages(String name){
        List<Page> pages = pageRepository.findByTagOrName(name, name);
        return pages.stream().map(this::mapFromPageToDto).collect(toList());
    }

    public PageDto refreshPage(Long id, PageDto pageDto) {
        Page page = pageRepository.findById(id).orElseThrow(() ->new PageNotFoundException("Id: "+ id));
        page.setName(pageDto.getName());
        page.setNeeded(pageDto.getNeeded());
        page.setTag(pageDto.getTag());
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate ld = LocalDate.parse(pageDto.getEndingDate(), DATEFORMATTER);
        page.setEndingDate(ld);
        return mapFromPageToDto(pageRepository.save(page));
    }

    public void makeDonate(MakeDonate donate){
        Donator donator = new Donator();
        donator.setAmount(donate.getAmount());
        Page page = pageRepository.findOneByName(donate.getPage());
        page.moneySumm(donate.getAmount());
        donator.setPage(page);
        donatorRepositoryRepository.save(donator);
        page.setDonator(donator);
        User user = userService.getCurrentDonator();
        user.setBonuses(page.getBonuses());
        user.setDonator(donator);
        page.setDonator(donator);
        pageRepository.save(page);
        userService.saveUser(user);
    }

    public void deletePage(Long id) {
        Page page = pageRepository.findById(id).orElseThrow(() ->new PageNotFoundException("Id: "+ id));
        pageRepository.delete(page);
    }
}
