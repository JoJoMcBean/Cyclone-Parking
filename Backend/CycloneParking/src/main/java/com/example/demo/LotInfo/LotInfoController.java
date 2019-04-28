package com.example.demo.LotInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LotInfoController {
    @Autowired
    LotInfoRepository lotInfoRepository;

    private final Logger logger = LoggerFactory.getLogger(LotInfoController.class);

    /**
     * Returns the lot info for a specified lot
     * @param lot       the lot the client want data for
     * @return          Lot Info data
     */
    @RequestMapping(method = RequestMethod.GET, path = "/lotInfo")
    public LotInfo getLotInfo(String lot){
        return lotInfoRepository.getLotInfo(lot);
    }


}
