package com.andysgrabek.dna.user;

import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    @GetMapping(value = "{id}")
    public UserDto getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping
    public UserDto postUser(@RequestBody NewUserDto user) throws NoSuchAlgorithmException {
        return userService.createUser(user);
    }

    @PutMapping
    public UserDto putUser(@RequestBody NewUserDto user) throws NoSuchAlgorithmException {
        return userService.updateUser(user);
    }

    @DeleteMapping(value = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
