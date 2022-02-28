package com.example.themarket.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public BigDecimal price;

    @Column
    public boolean active;

    @ManyToOne
    public User seller;

    @ManyToOne
    public User buyer;

    @OneToOne
    public Item item;

    public Contract() {
    }

    public Long getId() {
        return id;
    }

    public Contract setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Contract setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public Contract setActive(boolean active) {
        this.active = active;
        return this;
    }

    public User getSeller() {
        return seller;
    }

    public Contract setSeller(User seller) {
        this.seller = seller;
        return this;
    }

    public User getBuyer() {
        return buyer;
    }

    public Contract setBuyer(User buyer) {
        this.buyer = buyer;
        return this;
    }

    public Item getItem() {
        return item;
    }

    public Contract setItem(Item item) {
        this.item = item;
        return this;
    }
}
