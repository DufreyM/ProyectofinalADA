package com.example.demo;

import com.example.demo.controllers.UserController;
import com.example.demo.model.jpa.UserJpa;
import com.example.demo.model.mongo.MongoUser;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMongoUser_Success() {
        MongoUser mockMongoUser = new MongoUser();
        when(userService.createMongoUser(mockMongoUser)).thenReturn(mockMongoUser);

        ResponseEntity<MongoUser> response = userController.createMongoUser(mockMongoUser);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoUser, response.getBody());
        verify(userService, times(1)).createMongoUser(mockMongoUser);
    }

    @Test
    public void testCreateUserJpa_Success() {
        UserJpa mockUserJpa = new UserJpa();
        when(userService.createUserJpa(mockUserJpa)).thenReturn(mockUserJpa);

        ResponseEntity<UserJpa> response = userController.createUserJpa(mockUserJpa);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockUserJpa, response.getBody());
        verify(userService, times(1)).createUserJpa(mockUserJpa);
    }

    @Test
    public void testGetMongoUserById_Success() {
        MongoUser mockMongoUser = new MongoUser();
        when(userService.getMongoUserById("1")).thenReturn(mockMongoUser);

        ResponseEntity<MongoUser> response = userController.getMongoUserById("1");

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoUser, response.getBody());
        verify(userService, times(1)).getMongoUserById("1");
    }

    @Test
    public void testGetUserJpaById_Success() {
        UserJpa mockUserJpa = new UserJpa();
        when(userService.getUserJpaById(1L)).thenReturn(mockUserJpa);

        ResponseEntity<UserJpa> response = userController.getUserJpaById(1L);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockUserJpa, response.getBody());
        verify(userService, times(1)).getUserJpaById(1L);
    }

    @Test
    public void testGetAllMongoUsers_Success() {
        MongoUser mockMongoUser1 = new MongoUser();
        MongoUser mockMongoUser2 = new MongoUser();
        List<MongoUser> mockMongoUserList = Arrays.asList(mockMongoUser1, mockMongoUser2);

        when(userService.getAllMongoUsers()).thenReturn(mockMongoUserList);

        ResponseEntity<List<MongoUser>> response = userController.getAllMongoUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoUserList, response.getBody());
        verify(userService, times(1)).getAllMongoUsers();
    }

    @Test
    public void testGetAllJpaUsers_Success() {
        UserJpa mockUserJpa1 = new UserJpa();
        UserJpa mockUserJpa2 = new UserJpa();
        List<UserJpa> mockUserJpaList = Arrays.asList(mockUserJpa1, mockUserJpa2);

        when(userService.getAllJpaUsers()).thenReturn(mockUserJpaList);

        ResponseEntity<List<UserJpa>> response = userController.getAllJpaUsers();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockUserJpaList, response.getBody());
        verify(userService, times(1)).getAllJpaUsers();
    }

    @Test
    public void testUpdateMongoUser_Success() {
        MongoUser mockMongoUser = new MongoUser();
        when(userService.updateMongoUser("1", mockMongoUser)).thenReturn(mockMongoUser);

        ResponseEntity<MongoUser> response = userController.updateMongoUser("1", mockMongoUser);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockMongoUser, response.getBody());
        verify(userService, times(1)).updateMongoUser("1", mockMongoUser);
    }

    @Test
    public void testUpdateUserJpa_Success() {
        UserJpa mockUserJpa = new UserJpa();
        when(userService.updateUserJpa(1L, mockUserJpa)).thenReturn(mockUserJpa);

        ResponseEntity<UserJpa> response = userController.updateUserJpa(1L, mockUserJpa);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(mockUserJpa, response.getBody());
        verify(userService, times(1)).updateUserJpa(1L, mockUserJpa);
    }

    @Test
    public void testDeleteMongoUser_Success() {
        ResponseEntity<Void> response = userController.deleteMongoUser("1");

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).deleteMongoUser("1");
    }

    @Test
    public void testDeleteUserJpa_Success() {
        ResponseEntity<Void> response = userController.deleteUserJpa(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(userService, times(1)).deleteUserJpa(1L);
    }
}
