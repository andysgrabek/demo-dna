package com.andysgrabek.dna.user;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    private final UserRepository<Integer> userRepository;

    public UserController(UserRepository<Integer> userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/user")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable Integer id) {
        return userRepository.findById(id).orElseThrow();
    }

    @PostMapping(value = "/user")
    public User postUser(@RequestBody User user) {
        user.setCreationDate(new Date());
        return userRepository.saveAndFlush(user);
    }

    @PutMapping(value = "/user")
    public User putUser(@RequestBody User updatedUser) {
        return userRepository.findById(updatedUser.getId())
                .map(user -> {
                    user.setName(updatedUser.getName());
                    user.setPassword(updatedUser.getPassword());
                    return userRepository.save(user);
                })
                .orElseGet(() -> userRepository.save(updatedUser));
    }


    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable int id) {
        userRepository.deleteById(id);
    }
}
