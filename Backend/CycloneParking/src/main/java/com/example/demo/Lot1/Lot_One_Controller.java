package com.example.demo.Lot1;

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
public class Lot_One_Controller {

    @Autowired
    Lot_One_Repository lot1Repository;

    private final Logger logger = LoggerFactory.getLogger(Lot_One_Controller.class);

    @RequestMapping(method = RequestMethod.GET, path = "/lot1")
    public List<Lot_One> getLotInfo() {
        logger.info("Entered into Controller Layer");
        List<Lot_One> results = lot1Repository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/updatelot1")
    public Lot_One update(@Valid @RequestBody Lot_One updateSpot){
        if(lot1Repository.existsById(updateSpot.getSpotNum())) {
            return lot1Repository.save(updateSpot);
        }
        else return null;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getFilledlot1")
    public List<Integer> getFilled(){
        List<Integer> filledSpots = lot1Repository.getFilled();
        return filledSpots;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getEmptylot1")
    public List<Integer> getEmpty(){
        List<Integer> emptySpots = lot1Repository.getEmpty();
        return emptySpots;
    }
}
