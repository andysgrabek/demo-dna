package com.andysgrabek.dna.user;

import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository<Long> userRepository;
    private final PasswordService passwordService;

    public UserService(UserRepository<Long> userRepository, PasswordService passwordService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
    }

    public List<UserDto> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(x -> new UserDto(x.getId(), x.getName(), x.getCreationDate()))
                .toList();
    }

    public UserDto findById(Long id) {
        var user = userRepository.findById(id).orElseThrow();
        return new UserDto(user.getId(), user.getName(), user.getCreationDate());
    }

    public UserDto createUser(NewUserDto user) throws NoSuchAlgorithmException {
        var salt = passwordService.generateSalt();
        var newUser = new User(user.getLogin(), passwordService.hashPassword(user.getPassword(), salt), user.getName(), new Date(), salt);
        var createdUser = userRepository.saveAndFlush(newUser);
        return new UserDto(createdUser.getId(), createdUser.getName(), createdUser.getCreationDate());
    }

    public UserDto updateUser(NewUserDto user) throws NoSuchAlgorithmException {
        var u = userRepository.findById(user.getId()).orElseThrow();
        u.setName(user.getName());
        u.setPassword(passwordService.hashPassword(user.getPassword(), u.getSalt()));
        var updatedUser = userRepository.save(u);
        return new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getCreationDate());
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
