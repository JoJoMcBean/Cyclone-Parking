package com.example.demo.CurrentlyParked;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
public class CurrentlyParkedController {

    @Autowired
    CurrentlyParkedRepository currentlyParkedRepository;

    private final Logger logger = LoggerFactory.getLogger(CurrentlyParkedController.class);

    /**
     * This method returns all the rows in the Currently Parked table
     * @return list of all data in Currently Parked table
     */
    @RequestMapping(method = RequestMethod.GET, path = "/getFilled")
    public List<CurrentlyParked> getAllFilledSpots(){
        return currentlyParkedRepository.findAll();
    }


    /**
     * Returns all filled spots for a specific lot
     * @param lot   the specified lot the client wants data for
     * @return      list of data from the specified lot
     */
    @RequestMapping(method = RequestMethod.GET, path = "/getFilled/{lotid}")
    public List<Integer> getFilledSpots(@PathVariable("lotid") String lot){
        return currentlyParkedRepository.getFilledSpots(lot);
    }

    /**
     * Returns the user the status of their car
     * @param token     the unique token used to indentify each user
     * @return          returns a String of the information of where the user is parked
     */
    @RequestMapping(method = RequestMethod.POST, path = "/getParkedInfo")
    public String getParkedInfo(String token){
        logger.info("Token: " + token);
       String license = currentlyParkedRepository.getLicenseWithToken(token);
       //logger.info(currentlyParkedRepository.selectLicense(license).toString());
        return currentlyParkedRepository.selectLicense(license);

    }


    /**
     * Allows the user to leave a spot by deleting data from the currently parked table
     * This method also adds to the parking history after the user leaves
     * @param tokenpaid     the token of the user and how much they paid
     */
    @RequestMapping(method = RequestMethod.POST, path = "/leaveSpot")
    public void leaveSpot(String tokenpaid) {
        //String username = defaultUsersRepository.getUsernameFromToken(token)
        String [] info = tokenpaid.split(",");
        String token = info[0];
        Double paid = Double.parseDouble(info[1]);
        String username = currentlyParkedRepository.getUsernameFromToken(token);
        CurrentlyParked park = currentlyParkedRepository.selectEntry(username); //save the data that must be added to history

        currentlyParkedRepository.leaveSpot(username);
        //user has left spot


        //Now must add the entry to the parking_history table
        Instant instant = Instant.now();
        Long timeStampMillis = instant.toEpochMilli();
        if (currentlyParkedRepository.getEntriesPerUser(park.getUsername()) > 2) {
            currentlyParkedRepository.deleteOldest(park.getUsername());
        }

        currentlyParkedRepository.insertEntry(park.getUsername(), park.getLotid(), park.getSpotnum(),park.getTimeparked(), timeStampMillis, paid);

    }


    /**
     * This method takes a spot for a user by adding their data to the currently parked table
     * @param lotspottoken the lot and spot where the user parked and the user's token
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, path = "/takeSpot")
    public String takeSpot(String lotspottoken){
        String [] info = lotspottoken.split(",");
        String lot = info[0];
        Integer spot = Integer.parseInt(info[1]);
        String token = info[2];
        logger.info(lotspottoken);
        String license = currentlyParkedRepository.getLicenseWithToken(token);
        String username = currentlyParkedRepository.getUsernameFromToken(token);
        logger.info(username);
        CurrentlyParked park = new CurrentlyParked();
        park.setUsername(username);
        park.setLicense(license);
        park.setLotid(lot);
        park.setSpotnum(spot);

        Instant instant = Instant.now();
        Long timeStampMillis = instant.toEpochMilli();
        park.setTimeparked(timeStampMillis);

        try {
            currentlyParkedRepository.addParkedSpot(park.getUsername(), park.getLotid(), park.getSpotnum(), park.getLicense(), park.getTimeparked());
        }
        catch(ConstraintViolationException e){
            return null;
        }



        return park.toString();

    }

}
