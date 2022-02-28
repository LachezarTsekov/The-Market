package com.example.themarket.web;

import com.example.themarket.model.enums.CurrencyEnum;
import com.example.themarket.model.entity.User;
import com.example.themarket.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@RestController
@Api(tags = {"User Controller"})
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Create a user by following parameters: account, username and currency",
            notes = "Account must be positive number or zero",
            response = User.class)
    @PostMapping("/users/create")
    public User createUser(@RequestParam @Min(0) BigDecimal account,
                           @RequestParam  CurrencyEnum currency,
                           @RequestParam  @Min(3) @Max(20) String username) {

        return userService.create(account, currency, username);
    }
}
