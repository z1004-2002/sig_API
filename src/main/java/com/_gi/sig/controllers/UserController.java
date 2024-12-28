package com._gi.sig.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._gi.sig.dto.AuthUser;
import com._gi.sig.dto.UserDto;
import com._gi.sig.models.User;
import com._gi.sig.services.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
@Tag(name = "User")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserDto createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/authenticate")
    public UserDto authenticate(@RequestBody AuthUser authUser) throws Exception {
        return userService.authentication(authUser);
    }
    

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        return userService.getUsers();
    }

    @PutMapping("/update_password")
    public UserDto putMethodName(@RequestBody AuthUser authUser) throws Exception {
        return userService.updatePassword(authUser);
    }    
}
