package com.andysgrabek.dna.user;

import com.andysgrabek.dna.utility.Utility;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private final UserRepository<Long> userRepository;

    public UserService(UserRepository<Long> userRepository) {
        this.userRepository = userRepository;
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
        var newUser = new User(user.getLogin(), Utility.hashPassword(user.getPassword()), user.getName(), new Date());
        var createdUser = userRepository.saveAndFlush(newUser);
        return new UserDto(createdUser.getId(), createdUser.getName(), createdUser.getCreationDate());
    }

    public UserDto updateUser(NewUserDto user) throws NoSuchAlgorithmException {
        var u = userRepository.findById(user.getId()).orElseThrow();
        u.setName(user.getName());
        u.setPassword(Utility.hashPassword(user.getPassword()));
        var updatedUser = userRepository.save(u);
        return new UserDto(updatedUser.getId(), updatedUser.getName(), updatedUser.getCreationDate());
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
