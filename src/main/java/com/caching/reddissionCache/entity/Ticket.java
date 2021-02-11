package com.caching.reddissionCache.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "ticket_db")
public class Ticket implements Serializable {
    private static final long serialVersionUID = 2099219L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    @NonNull
    private String name;

    @NonNull
    @Column(name = "assignedTo")
    private String assignedTo;

    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    @Column(name = "assignedDate")
    private Date assignedDate;

    @Override
    public String toString() {
        return "TIcket{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", assignedTo='" + assignedTo + '\'' +
                ", assignedDate=" + assignedDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(Date assignedDate) {
        this.assignedDate = assignedDate;
    }

    public Ticket(String name, String assignedTo, Date assignedDate) {
        this.name = name;
        this.assignedTo = assignedTo;
        this.assignedDate = assignedDate;
    }

    public Ticket(int id, String name, String assignedTo, Date assignedDate) {
        this.id = id;
        this.name = name;
        this.assignedTo = assignedTo;
        this.assignedDate = assignedDate;
    }

    public Ticket() {
    }

}
