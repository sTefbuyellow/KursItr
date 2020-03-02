package com.pozoriche.controller;

import com.pozoriche.dto.MakeDonate;
import com.pozoriche.dto.PageDto;
import com.pozoriche.dto.UserDto;
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

    @GetMapping("/get/find/{name}")
    public ResponseEntity<List<PageDto>> findPages(@PathVariable @RequestBody String name){
        return new ResponseEntity<>(pageService.findPages(name), HttpStatus.OK);
    }

    @PutMapping("/refresh/{id}")
    public ResponseEntity<PageDto> refreshUser(@PathVariable Long id, @RequestBody PageDto pageDto){
        return new ResponseEntity<>(pageService.refreshPage(id, pageDto), HttpStatus.OK);
    }

    @PutMapping("/donate")
    public ResponseEntity donate(@RequestBody MakeDonate makeDonate){
        pageService.makeDonate(makeDonate);
        return new ResponseEntity(HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        pageService.deletePage(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
