package com.lab.controllers;

import com.lab.dto.CatDto;
import com.lab.dto.UserDto;
import com.lab.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/user/all")
    public List<UserDto> getAllUsers(){
        return userService.getAllUser();
    }
    @PostMapping("/user/create")
    public UserDto createUser(@RequestBody UserDto userDto){
        return userService.createUser(userDto);
    }
    @GetMapping("/profile/cats")
    public List<CatDto> getAllCats(){
        return userService.getAllCats();
    }
}
