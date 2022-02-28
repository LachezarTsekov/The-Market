package com.example.themarket.service.impl;

import com.example.themarket.model.entity.User;
import com.example.themarket.model.enums.CurrencyEnum;
import com.example.themarket.repository.UserRepository;
import com.example.themarket.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.InvalidParameterException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User create(BigDecimal account, CurrencyEnum currency, String username) {

        User newUser = new User();
        newUser.setCurrency(currency.toString().toLowerCase())
                .setUsername(username)
                .setAccount(account);

        return userRepository.save(newUser);
    }

    @Override
    public User getById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND
                        ,"User id is not valid"));
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


}
