package com.pozoriche.controller;

import com.pozoriche.dto.LoginRequest;
import com.pozoriche.dto.PageDto;
import com.pozoriche.dto.RegisterRequest;
import com.pozoriche.dto.UserDto;
import com.pozoriche.model.User;
import com.pozoriche.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/my-data")
    public ResponseEntity<RegisterRequest> getMyData(){
        return new ResponseEntity<>(userService.getMyData(), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe(){
        return new ResponseEntity<>(userService.getCurrentUser(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/user/{id}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable @RequestBody Long id){
        return new ResponseEntity<>(userService.getSingleUser(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/all")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/admin/refresh/{id}")
    public ResponseEntity<UserDto> refreshUser(@PathVariable Long id, @RequestBody UserDto userDto){
        return new ResponseEntity<>(userService.refreshUser(id, userDto), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity(HttpStatus.OK);
    }


}
