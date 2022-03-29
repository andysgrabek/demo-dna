package com.andysgrabek.dna;

import com.andysgrabek.dna.user.PasswordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@SpringBootTest
public class PasswordServiceTest {

    @Autowired
    private PasswordService passwordService;

    @Test
    public void passwordHashTestSamePasswordAndSalt() throws NoSuchAlgorithmException {
        var salt = passwordService.generateSalt();
        var password1 = passwordService.hashPassword("test1", salt);
        var password2 = passwordService.hashPassword("test1", salt);
        Assertions.assertArrayEquals(password1, password2);
    }

    @Test
    public void passwordHashTestSamePasswordDifferentSalt() throws NoSuchAlgorithmException {
        var password1 = passwordService.hashPassword("test1", passwordService.generateSalt());
        var password2 = passwordService.hashPassword("test1", passwordService.generateSalt());
        Assertions.assertFalse(Arrays.equals(password1, password2));
    }

}
