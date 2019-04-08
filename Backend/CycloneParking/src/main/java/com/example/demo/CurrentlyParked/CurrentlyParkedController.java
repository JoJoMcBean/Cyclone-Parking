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

    @RequestMapping(method = RequestMethod.GET, path = "/getFilled")
    public List<CurrentlyParked> getAllFilledSpots(){
        return currentlyParkedRepository.findAll();
    }


    @RequestMapping(method = RequestMethod.GET, path = "/getFilled/{lotid}")
    public List<Integer> getFilledSpots(@PathVariable("lotid") String lot){
        return currentlyParkedRepository.getFilledSpots(lot);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/getParkedInfo")
    public String getParkedInfo(String token){
        logger.info("Token: " + token);
       String license = currentlyParkedRepository.getLicenseWithToken(token);
       logger.info(currentlyParkedRepository.selectLicense(license).toString());
        return currentlyParkedRepository.selectLicense(license);

    }



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
