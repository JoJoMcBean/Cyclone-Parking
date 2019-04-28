package com.example.demo.Ticket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
public class TicketController {

    @Autowired
    TicketRepository tick_repo;

    private final Logger logger = LoggerFactory.getLogger(TicketController.class);

    /**
     * Adds a ticket to the ticket database
     * @param ticket  the ticket to add
     * @return     returns the ticket added
     */
    @RequestMapping(method = RequestMethod.POST, value = "/ticket/add")
    public Ticket addEntry(@Valid @RequestBody Ticket ticket){
        logger.info(ticket.toString());
        return tick_repo.save(ticket);
    }

    /**
     * Removes a ticket from the ticket database
     * @param ticket  the ticket to remove
     */
    @RequestMapping(method = RequestMethod.POST, value = "/ticket/remove")
    public void removeEntry(@Valid @RequestBody Ticket ticket){
        tick_repo.removeEntry(ticket.getDate(), ticket.getLicense());
    }

    /**
     * Returns all tickets for a particular user
     * @param license       license of user
     * @return              list of tickets
     */
    @RequestMapping(method = RequestMethod.GET, value = "/ticket/search/{license}")
    public List<Ticket> search(@PathVariable("license") String license){
        return tick_repo.searchByLicense(license);
    }

    /**
     * Returns all tickets on file
     * @return List of tickets
     */
    @RequestMapping(method = RequestMethod.GET, value = "/ticket/all")
    public List<Ticket> getAll(){
        return tick_repo.getAll();
    }

}
