package com.caching.reddissionCache.service;

import com.caching.reddissionCache.entity.Ticket;
import com.caching.reddissionCache.repository.TicketRepo;
import org.redisson.Redisson;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketServiceImpl implements TicketService {


    String ID_TO_TICKET_MAP="TICKET";
    @Autowired
    private TicketRepo ticketRepo;

    @Autowired
    RedissonClient redissonClient = Redisson.create();
    RMap<Integer,Ticket> ticketRMap = redissonClient.getMap(ID_TO_TICKET_MAP);

    @Override
    public Ticket saveTicket(Ticket ticket) {
        if(ticket.getAssignedTo()==null || ticket.getName()==null)
            throw new NullPointerException("Ticket Parameters are empty");
        ticketRMap.fastPut(ticket.getId(), ticket);
        return ticketRepo.save(ticket);
    }

    @Override
    public void deleteById(int id) {
        try {
            //TODO ask about removing from db
            ticketRepo.deleteById(id);
            ticketRMap.fastRemove(id);
        }
        catch (Exception e){
            throw new NullPointerException("Ticket ID Invalid !");
        }
    }

    @Override
    public Ticket findbyId(int id) {
        try {
            if(ticketRMap.containsKey(id)){
                System.out.println("Redis called");return  ticketRMap.get(id);}
            System.out.println("DB called");
            Ticket ticket = ticketRepo.findById(id).get();
            ticketRMap.fastPut(id,ticket);
            return ticket;

        }
        catch (Exception e){
            throw new NullPointerException("Ticket ID Invalid !");
        }
    }

    @Override
    public List<Ticket> findAll() {
        List<Ticket> ticketList = ticketRepo.findAll();
        return ticketList;
    }

    @Override
    public Ticket updateTicket(int id,Ticket ticket) {
        Ticket ticketDb = findbyId(id);
        if (ticket.getName()!=null)
            ticketDb.setName(ticket.getName());
        if (ticket.getAssignedTo()!=null)
            ticketDb.setAssignedTo(ticket.getAssignedTo());
        ticketRMap.fastReplace(id,ticketDb);
        return ticketDb;
    }

    @Override
    public Boolean ifIdExists(int id) {
        return ticketRepo.existsById(id);
    }
}
