package com.example.demo.ParkingHistory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class ParkingHistoryController {

    @Autowired
    ParkingHistoryRepository parkingHistoryRepository;

    private final Logger logger = LoggerFactory.getLogger(ParkingHistoryController.class);

    /**
     * Returns the parking history for a user
     * @param info      Contains the unique token of a user
     * @return          The complete parking history
     */
    @RequestMapping(method = RequestMethod.POST, path = "/history")
    public List<ParkingHistory> getHistory(@RequestBody Map<String, Object> [] info){
     String username = parkingHistoryRepository.getUsernameFromToken(info[0].get("token").toString());
     List<ParkingHistory> history = parkingHistoryRepository.getHistoryForUser(username);
     return history;
    }
}
