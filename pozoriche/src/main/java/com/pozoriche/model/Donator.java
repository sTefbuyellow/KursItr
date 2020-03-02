package com.pozoriche.model;

import javax.persistence.*;

@Entity
public class Donator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    private User donator;

    @ManyToOne
    private Page page;

    @Column
    private Long amount;



    public Donator(){}

    public Donator(User user){
        this.donator = user;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public User getDonator() {
        return donator;
    }

    public void setDonator(User donator) {
        this.donator = donator;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
