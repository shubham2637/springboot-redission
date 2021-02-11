package com.caching.reddissionCache.repository;

import com.caching.reddissionCache.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepo extends JpaRepository<Ticket,Integer> {
}
