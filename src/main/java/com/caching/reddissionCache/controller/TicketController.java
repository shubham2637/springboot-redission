package com.caching.reddissionCache.controller;

import com.caching.reddissionCache.entity.Ticket;
import com.caching.reddissionCache.service.TicketServiceImpl;
import org.redisson.spring.session.config.EnableRedissonHttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketServiceImpl ticketService;

    @GetMapping(value = "/all")
    public List<Ticket> getAllTickets(){
        return ticketService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Object getTicketById(@PathVariable int id){
        try {
            return ticketService.findbyId(id);
        }
        catch (Exception e){
            System.out.println("Ticket Save error "+e.getMessage());
            return "Ticket "+id+" not found!";
        }
    }

    @PostMapping()
    public Object saveTicket(@RequestBody Ticket ticket){
        try {
            return ticketService.saveTicket(ticket);
        }
        catch (Exception e){
            System.out.println("Ticket Save error "+e.getMessage());
            return "Ticket Can't be Saved! Please check the Request Body";
        }
    }

    @DeleteMapping("/{id}")
    public Object deleteById(@PathVariable int id){
        try {
            ticketService.deleteById(id);
            return "Ticket with id "+id+" deleted.";
        }
        catch (Exception e){
            System.out.println("Ticket Delete error "+e.getMessage());
            return "Ticket "+id+" not found!";
        }
    }

    @PutMapping("/{id}")
    public Object updateById(@RequestBody Ticket ticket,@PathVariable int id) {
        try {
            if (ticketService.findbyId(id)!=null) {
                System.out.println("Ticket Found!");
                Ticket updatedTicket = ticketService.updateTicket(id,ticket);
                ticketService.saveTicket(updatedTicket);
                return updatedTicket;
            }
            else return "Ticket id not found!";
        } catch (Exception e) {
            System.out.println("Ticket Update error " + e.getMessage());
            return "Ticket not updated!";
        }
    }
}



