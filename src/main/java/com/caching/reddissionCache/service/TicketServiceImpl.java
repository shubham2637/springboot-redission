package com.caching.reddissionCache.service;

import com.caching.reddissionCache.entity.Ticket;
import com.caching.reddissionCache.repository.TicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepo ticketRepo;

    @Override
    public Ticket saveTicket(Ticket ticket) {
        if(ticket.getAssignedTo()==null || ticket.getName()==null)
            throw new NullPointerException("Ticket Parameters are empty");
        return ticketRepo.save(ticket);
    }

    @Override
    public void deleteById(int id) {
        try {
            ticketRepo.deleteById(id);
        }
        catch (Exception e){
            throw new NullPointerException("Ticket ID Invalid !");
        }
    }

    @Override
    public Ticket findbyId(int id) {
        try {
            return ticketRepo.findById(id).get();
        }
        catch (Exception e){
            throw new NullPointerException("Ticket ID Invalid !");
        }
    }

    @Override
    public List<Ticket> findAll() {
        return ticketRepo.findAll();
    }

    @Override
    public Ticket updateTicket(int id,Ticket ticket) {
        Ticket ticketDb = findbyId(id);
        if (ticket.getName()!=null)
            ticketDb.setName(ticket.getName());
        if (ticket.getAssignedTo()!=null)
            ticketDb.setAssignedTo(ticket.getAssignedTo());
        return ticketDb;
    }

    @Override
    public Boolean ifIdExists(int id) {
        return ticketRepo.existsById(id);
    }
}
