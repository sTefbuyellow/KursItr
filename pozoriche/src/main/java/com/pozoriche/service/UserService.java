package com.pozoriche.service;

import com.pozoriche.dto.UserDto;
import com.pozoriche.exception.UserNotFoundException;
import com.pozoriche.model.User;
import com.pozoriche.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class UserService {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;


    public UserDto getCurrentUser(){
        org.springframework.security.core.userdetails.User principal = authService.getCurrentUser().orElseThrow(()->
                new IllegalArgumentException("No User logged in"));
        User user = userRepository.findUserByUserName(principal.getUsername());
        System.out.print(principal.getAuthorities());
        return mapFromUserToDto(user);
    }

    private UserDto mapFromUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }

    public List<UserDto> getAllUsers(){
        List<User> pages = userRepository.findAll();
        return pages.stream().map(this::mapFromUserToDto).collect(toList());
    }

    public UserDto getSingleUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("id: "+id));
        return mapFromUserToDto(user);
    }
}
