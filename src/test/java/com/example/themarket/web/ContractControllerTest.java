package com.example.themarket.web;

import com.example.themarket.model.entity.Contract;
import com.example.themarket.model.entity.Item;
import com.example.themarket.model.entity.User;
import com.example.themarket.model.view.ContractViewModel;
import com.example.themarket.repository.ContractRepository;
import com.example.themarket.repository.ItemRepository;
import com.example.themarket.repository.UserRepository;
import com.example.themarket.service.ContractService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class ContractControllerTest {

    private MockMvc mockMvc;
    private User seller;
    private User buyer;
    private Item item;
    private Item secondItem;

    @Autowired
    private WebApplicationContext web;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ContractService contractService;

    @BeforeEach
    void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(web).build();

        seller = new User();
        seller.setAccount(BigDecimal.valueOf(100))
                .setUsername("SellerTest")
                .setCurrency("bgn");
        buyer = new User();
        buyer.setAccount(BigDecimal.valueOf(150))
                .setUsername("BuyerTest")
                .setCurrency("usd");
        seller = userRepository.save(seller);
        buyer = userRepository.save(buyer);

        item = new Item();
        item.setOwner(seller)
                .setName("TestItem");
        secondItem = new Item();
        secondItem.setOwner(buyer)
                .setName("TestSecondItem");

        item = itemRepository.save(item);
        secondItem = itemRepository.save(secondItem);
    }



    @Test
    void createContract() throws Exception {

        mockMvc.perform(
                post("/contract/create")
                        .param("price", "" + BigDecimal.valueOf(10))
                        .param("itemId", "" + item.getId()))
                .andExpect(status().isOk())
                .andExpect(content().json("{" +
                        "'sellerId': 1," +
                        "'itemId': 1," +
                        "'price': 10," +
                        "'active': true}"));
    }


    @Test
    void getAllBySellerId() throws Exception {
        Contract testContract = new Contract();
        testContract.setActive(true)
                .setSeller(seller)
                .setItem(item)
                .setPrice(BigDecimal.valueOf(10));
        Contract test2 = new Contract();
        test2.setActive(true)
                .setSeller(buyer)
                .setItem(secondItem)
                .setPrice(BigDecimal.valueOf(10));
        contractRepository.save(testContract);
        contractRepository.save(test2);


        mockMvc.perform(
                get("/contract/get-all-by-seller-id")
                .param("sellerId", "1")
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(1)));
    }


}