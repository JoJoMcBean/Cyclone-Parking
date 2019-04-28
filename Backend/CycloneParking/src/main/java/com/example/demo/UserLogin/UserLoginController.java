package com.example.demo.UserLogin;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;


@RestController
@EnableWebMvc
public class UserLoginController {


    @Autowired
    UserLoginRepository user_loginRepository;

    private final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    /**
     * Adds a user to the user_login table
     * @param user      the user data to add
     * @return          the user that was added
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add/user")
    public UserLogin createUser(@Valid @RequestBody UserLogin user) {

            if(!user_loginRepository.existsById(user.getUsername()) && user.getUsername() != "" && user.getPassword() != "" && user.getUser_type() != "" && user.getEmail() != "") {
               return user_loginRepository.save(user);
            }
            return null;

        }

    /**
     * Updates user_login table
     * @param updateUser  new user data
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update")
    public UserLogin update(@Valid @RequestBody UserLogin updateUser){
        if(user_loginRepository.existsById(updateUser.getUsername())) {
            return user_loginRepository.save(updateUser);
        }
        else return null;
    }


    /**
     * Returns all users in the user_login table
     * @return  list of all users
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public List<UserLogin> getAllUsers() {
        logger.info("Entered into Controller Layer");
        List<UserLogin> results = user_loginRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }

    /**
     * Returns user info for a specified username
     * @param username
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/users/{username}")
    public UserLogin findUserByUsername(@PathVariable("username") String username) {
        return user_loginRepository.getUser(username);
    }

    /**
     * Returns all username in the user_login table
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/usernames")
    public List<String> getUsernames(){
        List<String> results = user_loginRepository.getUsernames();
        return results;
    }

    /**
     * Checks if the user has inputted their correct password
     * @param userpass
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/authentication")
    public String login(String userpass) {
        String[] login = userpass.split(",");

        String username = login[0];
        String password = login[1];
        logger.info(login[0]);
        logger.info(login[1]);
        UserLogin userCheck = user_loginRepository.getUser(username);
        String token = null;
        if(userCheck.getPassword().equals(password) && user_loginRepository.existsById(username)){
            token = String.valueOf(UUID.randomUUID());
            user_loginRepository.addToken(token, username);
            return token + "," + userCheck.getUser_type();
        }
        else{
            return "failed";
        }

    }

    @RequestMapping(method = RequestMethod.POST, path = "/getUsernameByToken")
    public String getUsernameByToken(String token) {
        String username = user_loginRepository.getUsernameByToken(token);
        return username;
    }

}
