package com.andysgrabek.dna;

import com.andysgrabek.dna.user.UserController;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void testGetAllUsers() {
        Assertions.assertDoesNotThrow(() -> userController.getAllUsers());
    }

    @Test
    public void testGetUserFailsNoId() {
        Assertions.assertThrows(InvalidDataAccessApiUsageException.class, () -> userController.getUser(null));
    }

    @Test
    public void testGetUserById() {
        var firstId = userController.getAllUsers().stream().findFirst().orElseThrow().getId();
        Assertions.assertEquals(firstId, userController.getUser(firstId).getId());
    }

}
