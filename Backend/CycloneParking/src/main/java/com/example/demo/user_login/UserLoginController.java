package com.example.demo.user_login;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;


@RestController
@EnableWebMvc
class UserLoginController {

    @Autowired
    UserLoginRepository user_loginRepository;
    userService userService;

    private final Logger logger = LoggerFactory.getLogger(UserLoginController.class);


    @RequestMapping(method = RequestMethod.POST, value = "/add/user")
    public UserLogin createUser(@Valid @RequestBody UserLogin user) {

            if(!user_loginRepository.existsById(user.getUsername()) && user.getUsername() != "" && user.getPassword() != "" && user.getUser_type() != "" && user.getEmail() != "") {
               return user_loginRepository.save(user);
            }
            return null;

        }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public UserLogin update(@Valid @RequestBody UserLogin updateUser){
        if(user_loginRepository.existsById(updateUser.getUsername())) {
            return user_loginRepository.save(updateUser);
        }
        else return null;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public UserLogin deleteUser(@Valid @RequestBody UserLogin deleteUser){
        if(user_loginRepository.existsById(deleteUser.getUsername())) {
            user_loginRepository.delete(deleteUser);
            return deleteUser;
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public List<UserLogin> getAllUsers() {
        logger.info("Entered into Controller Layer");
        List<UserLogin> results = user_loginRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/users/{username}")
    public UserLogin findUserByUsername(@PathVariable("username") String username) {
        //logger.info("Entered into Controller Layer");
        //Optional<UserLogin> results = user_loginRepository.findById(username);
        return user_loginRepository.getUser(username);
    }
    @RequestMapping(method = RequestMethod.GET, path = "/usernames")
    public List<String> getUsernames(){
        List<String> results = user_loginRepository.getUsernames();
        return results;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/authentication")
    public String login(@RequestBody String userpass) {
        String parsed = "";
        Scanner s = new Scanner(userpass);
        while(s.hasNext()){
            if(s.next().contains(":")){
                parsed = s.next();
            }
        }
        parsed = parsed.substring(1, parsed.length() - 1);
        String[] login = parsed.split(",");

        String username = login[0];
        String password = login[1];
        //logger.info(login[0]);
        //logger.info(login[1]);
        UserLogin userCheck = user_loginRepository.getUser(username);
        logger.info(userCheck.getPassword());
        String token = null;
        if(userCheck.getPassword().equals(password) && user_loginRepository.existsById(username)){
            token = String.valueOf(UUID.randomUUID());
            user_loginRepository.addToken(token, username);
            return token;
        }
        else{
            return "failed";
        }

    }

}
