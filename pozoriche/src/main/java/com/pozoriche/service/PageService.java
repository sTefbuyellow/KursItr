package com.pozoriche.service;

import com.pozoriche.dto.PageDto;
import com.pozoriche.exception.PageNotFoundException;
import com.pozoriche.model.Page;
import com.pozoriche.repos.PageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PageService {

    @Autowired
    private AuthService authService;

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
        pageDto.setUsername(page.getUsername());
        pageDto.setTag(page.getTag());
        return pageDto;
    }

    public Page mapFromDtoToPage(PageDto pageDto){
        Page page = new Page();
        page.setTag(pageDto.getTag());
        page.setName(pageDto.getName());
        User user = authService.getCurrentUser().orElseThrow(()->
                new IllegalArgumentException("No User logged in"));
        page.setUsername(user.getUsername());
        return page;
    }

    public void createPage(PageDto pageDto){
        Page page = mapFromDtoToPage(pageDto);
        pageRepository.save(page);
    }
}
