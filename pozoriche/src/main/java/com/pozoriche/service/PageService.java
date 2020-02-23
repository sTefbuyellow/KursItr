package com.pozoriche.service;

import com.pozoriche.dto.PageDto;
import com.pozoriche.exception.PageNotFoundException;
import com.pozoriche.model.Page;
import com.pozoriche.repos.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        pageDto.setCreationDate(page.getCreationDate());
        pageDto.setEndingDate(page.getEndingDate());
        pageDto.setImages(page.getImages());
        pageDto.setMoney(page.getMoney());
        return pageDto;
    }

    public Page mapFromDtoToPage(PageDto pageDto){
        Page page = new Page();
        page.setTag(pageDto.getTag());
        page.setName(pageDto.getName());
        page.setCategories(pageDto.getCategories());
        page.setCreationDate(LocalDateTime.now());
        page.setEndingDate(pageDto.getEndingDate());
        page.setImages(pageDto.getImages());
        page.setMoney(pageDto.getMoney());
        page.setYouTubeVideo(pageDto.getYouTubeVideo());
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

}
