package com.example.demo.DefaultUsers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class DefaultUsersController {

    @Autowired
    DefaultUsersRepository defaultUsersRepository;

    private final Logger logger = LoggerFactory.getLogger(DefaultUsersController.class);

    /**
     * This method returns the information of all default users
     * @return  list of default users data
     */
    @RequestMapping(method = RequestMethod.GET, path = "/defaultusers")
    public List<DefaultUsers> getDefaultUsers() {
        logger.info("Entered into Controller Layer");
        List<DefaultUsers> results = defaultUsersRepository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }

    /**
     * This method adds data into the default_users table
     * @param user  the user information
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/add/default")
    public DefaultUsers createUser(@Valid @RequestBody DefaultUsers user) {

        if(!defaultUsersRepository.existsById(user.getUsername())) {
            return defaultUsersRepository.save(user);
        }
        return null;

    }

    /**
     * Returns the default user info
     * @param token     the unique token for each user
     * @return      String of user info
     */
    @RequestMapping(method = RequestMethod.POST, value = "/get/default")
    public String getUserInfoOnToken(String token){

        logger.info(token);

        if(token == null){
            return "null token";
        }
        String userInfo = defaultUsersRepository.getUserInfoWithToken(token);
        logger.info(userInfo);
        return userInfo;
    }

    /**
     * Updates user info by changing the data in the db
     * @param updateUser       the user info to be updated
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update/default")
    public DefaultUsers update(@Valid @RequestBody DefaultUsers updateUser){
        if(defaultUsersRepository.existsById(updateUser.getUsername())) {
            return defaultUsersRepository.save(updateUser);
        }
        else return null;
    }

    /**
     * updates information for user_login
     * @param userInfo
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/update/userinfo")
    public String updateInfo(String userInfo){
        String[] infoArray = userInfo.split(",");
        String password = infoArray[0];
        String email = infoArray[1];
        String license = infoArray[2];
        String cardNum = infoArray[3];
        String token = infoArray[4];

        String username = defaultUsersRepository.getUsernameFromToken(token);
        defaultUsersRepository.updateUserLoginInfo(password, email, token);
        defaultUsersRepository.updateDefaultUsersInfo(license, cardNum, username);

        return "Success";

    }





}
