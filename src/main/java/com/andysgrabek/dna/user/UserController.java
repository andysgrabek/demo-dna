package com.andysgrabek.dna.user;

import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping(value = "/user")
    public UserDto postUser(@RequestBody NewUserDto user) throws NoSuchAlgorithmException {
        return userService.createUser(user);
    }

    @PutMapping(value = "/user")
    public UserDto putUser(@RequestBody NewUserDto user) throws NoSuchAlgorithmException {
        return userService.updateUser(user);
    }


    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
