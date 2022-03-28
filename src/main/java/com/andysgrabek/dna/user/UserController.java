package com.andysgrabek.dna.user;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {

    private final UserRepository<Long> userRepository;

    public UserController(UserRepository<Long> userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping(value = "/user")
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(x -> new UserDto(x.getId(), x.getName(), x.getCreationDate()))
                .toList();
    }

    @GetMapping(value = "/user/{id}")
    public UserDto getUser(@PathVariable Long id) {
        var user = userRepository.findById(id).orElseThrow();
        return new UserDto(user.getId(), user.getName(), user.getCreationDate());
    }

    @PostMapping(value = "/user")
    public UserDto postUser(@RequestBody User user) {
        user.setCreationDate(new Date());
        var createdUser = userRepository.saveAndFlush(user);
        return new UserDto(createdUser.getId(), createdUser.getName(), createdUser.getCreationDate());
    }

    @PutMapping(value = "/user")
    public UserDto putUser(@RequestBody User user) {
        var updatedUser = userRepository.findById(user.getId())
                .map(u -> {
                    u.setName(user.getName());
                    u.setPassword(user.getPassword());
                    return userRepository.save(u);
                }).orElseThrow();
        return new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getCreationDate());
    }


    @DeleteMapping(value = "/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }
}
