package com.example.demo.Lot0;

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
public class Lot_Zero_Controller {

    @Autowired
    Lot_Zero_Repository lot0Repository;

    private final Logger logger = LoggerFactory.getLogger(Lot_Zero_Controller.class);

    @RequestMapping(method = RequestMethod.GET, path = "/lot0")
    public List<Lot_Zero> getLotInfo() {
        logger.info("Entered into Controller Layer");
        List<Lot_Zero> results = lot0Repository.findAll();
        logger.info("Number of Records Fetched:" + results.size());
        return results;
    }


    @RequestMapping(method = RequestMethod.POST, value = "/updatelot0")
    public Lot_Zero update(@Valid @RequestBody Lot_Zero updateSpot){
        if(lot0Repository.existsById(updateSpot.getSpotNum())) {
            return lot0Repository.save(updateSpot);
        }
        else return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getFilledlot0")
    public List<Integer> getFilled(){
        List<Integer> filledSpots = lot0Repository.getFilled();
        return filledSpots;
    }
    @RequestMapping(method = RequestMethod.GET, value = "/getEmptylot0")
    public List<Integer> getEmpty(){
        List<Integer> emptySpots = lot0Repository.getEmpty();
        return emptySpots;
    }
}
