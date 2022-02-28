package com.example.themarket.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String username;

    @Column
    public BigDecimal account;

    @Column
    public String currency;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public BigDecimal getAccount() {
        return account;
    }

    public User setAccount(BigDecimal account) {
        this.account = account;
        return this;
    }

    public String getCurrency() {
        return currency;
    }

    public User setCurrency(String currency) {
        this.currency = currency;
        return this;
    }
}
