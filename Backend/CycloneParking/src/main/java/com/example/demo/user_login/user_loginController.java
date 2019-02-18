package com.example.demo.user_login;


import com.fasterxml.jackson.databind.util.JSONPObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@EnableWebMvc
class user_loginController {

    @Autowired
    user_loginRepository user_loginRepository;
    userService userService;

    private final Logger logger = LoggerFactory.getLogger(user_loginController.class);


    @RequestMapping(method = RequestMethod.POST, value = "/add/user")
    public user_login createUser(@Valid @RequestBody user_login user) {

            if(!user_loginRepository.existsById(user.getUsername()) && user.getUsername() != "" && user.getPassword() != "" && user.getUser_type() != "" && user.getEmail() != "") {
               return user_loginRepository.save(user);
            }
            return null;

        }

    @RequestMapping(method = RequestMethod.PUT, value = "/update")
    public user_login update(@Valid @RequestBody user_login updateUser){
        if(user_loginRepository.existsById(updateUser.getUsername())) {
            return user_loginRepository.save(updateUser);
        }
        else return null;
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/delete")
    public user_login deleteUser(@Valid @RequestBody user_login deleteUser){
        if(user_loginRepository.existsById(deleteUser.getUsername())) {
            user_loginRepository.delete(deleteUser);
            return deleteUser;
        }
        return null;
    }


    @RequestMapping(method = RequestMethod.GET, path = "/users")
    public List<user_login> getAllUsers() {
        logger.info("Entered into Controller Layer");
        List<user_login> results = user_loginRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/users/{username}")
    public Optional<user_login> findUserByUsername(@PathVariable("username") String username) {
        logger.info("Entered into Controller Layer");
        Optional<user_login> results = user_loginRepository.findById(username);
        return results;
    }
    @RequestMapping(method = RequestMethod.GET, path = "/usernames")
    public List<String> getUsernames(){
        List<String> results = user_loginRepository.getUsernames();
        return results;
    }
    

}
