package com.pozoriche.service;

import com.pozoriche.dto.RegisterRequest;
import com.pozoriche.dto.UserDto;
import com.pozoriche.exception.UserNotFoundException;
import com.pozoriche.model.User;
import com.pozoriche.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;

    public User getCurrentDonator(){
        org.springframework.security.core.userdetails.User principal = authService.getCurrentUser().orElseThrow(()->
                new IllegalArgumentException("No User logged in"));
        return userRepository.findUserByUserName(principal.getUsername());
    }

    public void saveUser(User user){
        userRepository.save(user);
    }

    public UserDto getCurrentUser(){
        org.springframework.security.core.userdetails.User principal = authService.getCurrentUser().orElseThrow(()->
                new IllegalArgumentException("No User logged in"));
        User user = userRepository.findUserByUserName(principal.getUsername());
        return mapFromUserToDto(user);
    }

    private UserDto mapFromUserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        userDto.setBonusList(user.getBonusList());
        userDto.setDonated(user.getDonatedList());
        userDto.setPages(user.getPagesList());
        return userDto;
    }

    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapFromUserToDto).collect(toList());
    }

    public UserDto getSingleUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("id: "+id));
        return mapFromUserToDto(user);
    }

    public RegisterRequest getMyData(){
        org.springframework.security.core.userdetails.User principal = authService.getCurrentUser().orElseThrow(()->
                new IllegalArgumentException("No User logged in"));
        User user = userRepository.findUserByUserName(principal.getUsername());
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setUsername(user.getUserName());
        registerRequest.setPassword(passwordEncoder.encode(user.getPassword()));
        registerRequest.setEmail(user.getEmail());
        return registerRequest;
    }

    public UserDto refreshUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("id: "+id));
        user.setUserName(userDto.getUserName());
        user.setRole(userDto.getRole());
        user.setEmail(userDto.getEmail());
        return  mapFromUserToDto(userRepository.save(user));
    }

    public void deleteUser(Long id) {
        userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("id: "+id));
        userRepository.deleteById(id);
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("id: "+id));
    }

    public User getUserByName(String name){
        return userRepository.findByUserName(name).orElseThrow(()-> new UserNotFoundException("name: "+ name));
    }
}
