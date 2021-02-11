package com.caching.reddissionCache.service;

import com.caching.reddissionCache.entity.Ticket;

import java.util.List;

public interface TicketService {
    Ticket saveTicket(Ticket tIcket);
    void deleteById(int id);
    Ticket findbyId(int id);
    List<Ticket> findAll();
    Ticket updateTicket(int id,Ticket ticket);
    Boolean ifIdExists(int id);
}
