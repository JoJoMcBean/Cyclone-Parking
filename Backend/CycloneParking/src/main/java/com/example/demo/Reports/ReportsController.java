package com.example.demo.Reports;

import com.example.demo.UserLogin.UserLoginController;
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
public class ReportsController {

    @Autowired
    ReportsRepository reportsRepository;

    private final Logger logger = LoggerFactory.getLogger(UserLoginController.class);

    /**
     * Returns all reports on file
     * @return List of all reports
     */
    @RequestMapping(method = RequestMethod.GET, value = "/getReports")
    public List<Reports> getReports() {

        List<Reports> results = reportsRepository.getReportsByDate();
        return results;

    }

    /**
     * Allows the client to file a report and adds it to the Reports database
     * @param reportSent  the report to add to the database
     */
    @RequestMapping(method = RequestMethod.POST, value = "/fileReport")
    public void addReport(@RequestBody Map<String, Object> reportSent) {

            logger.info(reportSent.toString());
            String filedby = reportSent.get("filedby").toString();
            String lot = reportSent.get("lot").toString();
            Integer spot = Integer.parseInt(reportSent.get("spot").toString());
            String description = reportSent.get("description").toString();
            Long filetime = Long.parseLong(reportSent.get("filetime").toString());



            logger.info(filedby);
            logger.info(lot);
            logger.info(Integer.toString(spot));
            logger.info(description);
            logger.info(Long.toString(filetime));

            reportsRepository.fileReport(filedby, lot, spot, description, filetime);

    }

    /**
     * Deletes a report from the database
     * @param rid   the id of the report to delete
     * @return      "deleted" if succesful
     */
    @RequestMapping(method = RequestMethod.POST, value = "/deleteReport")
    public String deleteReport(String rid) {
        logger.info(rid);
        Integer id = Integer.parseInt(rid);
        reportsRepository.deleteById(id);
        return "deleted";
    }







}
