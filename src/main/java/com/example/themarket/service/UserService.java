package com.example.themarket.service;

import com.example.themarket.model.entity.User;
import com.example.themarket.model.enums.CurrencyEnum;

import java.math.BigDecimal;

public interface UserService {
    User create(BigDecimal account, CurrencyEnum currency, String username);

    User getById(Long id);

    void save(User seller);
}
