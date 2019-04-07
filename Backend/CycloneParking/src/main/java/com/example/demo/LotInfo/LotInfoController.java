package com.example.demo.LotInfo;

import org.hibernate.validator.constraints.pl.REGON;
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
public class LotInfoController {
    @Autowired
    LotInfoRepository lotInfoRepository;

    private final Logger logger = LoggerFactory.getLogger(LotInfoController.class);

    @RequestMapping(method = RequestMethod.GET, path = "/lotInfo")
    public LotInfo getLotInfo(String lot){
        return lotInfoRepository.getLotInfo(lot);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/addLotInfo")
        public LotInfo addLotInfo(@RequestBody LotInfo lotinfo){
            lotInfoRepository.save(lotinfo);
            return lotinfo;
        }

}
