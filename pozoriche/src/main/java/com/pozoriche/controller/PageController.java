package com.pozoriche.controller;

import com.pozoriche.dto.PageDto;
import com.pozoriche.model.Page;
import com.pozoriche.repos.PageRepository;
import com.pozoriche.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pages")
public class PageController {

    @Autowired
    private PageService pageService;

    @PostMapping
    public ResponseEntity createPage(@RequestBody PageDto pageDto){
        pageService.createPage(pageDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PageDto>> getAllPages(){
        return new ResponseEntity<>(pageService.getAllPages(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<PageDto> getSinglePage(@PathVariable @RequestBody Long id){
        return new ResponseEntity<>(pageService.getSinglePage(id), HttpStatus.OK);
    }
}
