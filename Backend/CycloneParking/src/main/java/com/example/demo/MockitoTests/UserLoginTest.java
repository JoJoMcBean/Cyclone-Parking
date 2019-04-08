package com.example.demo.MockitoTests;

import com.example.demo.UserLogin.UserLogin;
import com.example.demo.UserLogin.UserLoginRepository;
import com.example.demo.UserLogin.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserLoginTest {

    @InjectMocks
    UserService service;

    @Mock
    UserLoginRepository repo;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getUserByUsernameTest() {
       when(repo.getUser("mockito")).thenReturn(new UserLogin("mockito", "pass", "default", "email", "token"));

       UserLogin user = service.getUser("mockito");

       assertEquals("mockito", user.getUsername());
       assertEquals("pass", user.getPassword());
       assertEquals("default", user.getUser_type());
       assertEquals("email", user.getEmail());
       assertEquals("token", user.getToken());
    }

    @Test
    public void getAllUsersTest(){
        List<UserLogin> list = new ArrayList<UserLogin>();
        UserLogin userOne = new UserLogin("user1", "pass1", "default", "john@gmail.com", "token");
        UserLogin userTwo = new UserLogin("user2", "pass2", "default", "john2@gmail.com", "token2");
        UserLogin userThree = new UserLogin("user3", "pass3", "default", "john3@gmail.com", "token3");

        list.add(userOne);
        list.add(userTwo);
        list.add(userThree);

        when(repo.findAll()).thenReturn(list);

        List<UserLogin> users = service.getAllUsers();

        assertEquals(3, users.size());
        assertEquals(userOne, users.get(0));
        assertEquals(userTwo, users.get(1));
        assertEquals(userThree, users.get(2));
        verify(repo, times(1)).findAll();

    }

    @Test
    public void addAllUsernames(){
        List<String> usernames = new ArrayList<String>();
        String user1 = "username1";
        String user2 = "username2";
        String user3 = "username3";

        usernames.add(user1);
        usernames.add(user2);
        usernames.add(user3);

        when(repo.getUsernames()).thenReturn(usernames);

        List<String> allUsernames = service.getUsernames();

        assertEquals(3, allUsernames.size());
        assertEquals("username1", allUsernames.get(0));
        assertEquals("username2", allUsernames.get(1));
        assertEquals("username3", allUsernames.get(2));
        verify(repo, times(1)).getUsernames();

    }







}
