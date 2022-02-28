package com.example.themarket.service.impl;

import com.example.themarket.model.entity.Contract;
import com.example.themarket.model.entity.Item;
import com.example.themarket.model.entity.User;
import com.example.themarket.model.projection.ActiveContractProjection;
import com.example.themarket.model.view.AllActiveContractViewModel;
import com.example.themarket.model.view.ContractViewModel;
import com.example.themarket.model.view.CreateContractViewModel;
import com.example.themarket.repository.ContractRepository;
import com.example.themarket.repository.ItemRepository;
import com.example.themarket.repository.UserRepository;
import com.example.themarket.service.ItemService;
import com.example.themarket.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ContractServiceImplTest {

    private ContractServiceImpl toTest;
    private Item item;
    private User buyer;
    private User seller;
    private Contract contract;

    @Mock
    private ContractRepository contractRepository;
    @Mock
    private ItemService itemService;
    @Mock
    private UserService userService;
    @Mock
    private CurrencyRateApiConfiguration currencyApi;
    @Mock
    private ActiveContractProjection activeContractProjection;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ModelMapper modelMapper;


    @BeforeEach
    void init() {
        toTest = new ContractServiceImpl(contractRepository, itemService, userService, currencyApi, modelMapper);

        seller = new User();
        seller
                .setId(2L)
                .setAccount(BigDecimal.valueOf(100))
                .setUsername("TestSeller")
        .setCurrency("bgn");

        buyer = new User();
        buyer
                .setId(3L)
                .setAccount(BigDecimal.valueOf(100))
                .setUsername("TestBuyer")
        .setCurrency("bgn");

        item = new Item();
        item
                .setId(1L)
                .setName("Shoes")
                .setOwner(seller);

        contract = new Contract();
        contract.setId(1L)
                .setActive(true)
                .setPrice(BigDecimal.valueOf(1))
                .setBuyer(null)
                .setItem(item)
                .setSeller(seller);

        userRepository.save(buyer);
        userRepository.save(seller);
        itemRepository.save(item);
        contractRepository.save(contract);

    }

    @Test
    void createContract() {
        when(itemService.findById(item.getId())).thenReturn(item);
        CreateContractViewModel contract
                = toTest.createContract(item.getId(), BigDecimal.valueOf(1));

        ContractViewModel expected = new ContractViewModel()
                .setActive(true)
                .setPrice(BigDecimal.valueOf(1))
                .setItemId(1L)
                .setSellerId(2L);

        assertNotNull(contract);
        assertEquals(contract.getItemId(), expected.getItemId());
        assertEquals(contract.getPrice(), expected.getPrice());
        assertEquals(contract.getSellerId(), expected.getSellerId());
        assertEquals(contract.isActive(), expected.isActive());
    }

    @Test
    void getAllActive() {
        ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

        ActiveContractProjection projection = factory.createProjection(ActiveContractProjection.class);
        projection.setActive(true);
        ActiveContractProjection projection1 = factory.createProjection(ActiveContractProjection.class);
        projection1.setActive(true);
        when(contractRepository.getAllActive())
                .thenReturn(List.of(projection, projection1));

        List<AllActiveContractViewModel> allActive = toTest.getAllActive();

        assertEquals(2, allActive.size());
    }

    @Test
    void closeContract() throws IOException {
        when(contractRepository.getByItemId(any())).thenReturn(contract);
        when(currencyApi.currencyRate(buyer.currency, seller.currency)).thenReturn(BigDecimal.valueOf(1.3), BigDecimal.valueOf(0.7));

        when(userService.getById(3L)).thenReturn(buyer);

        Contract closeContract = toTest.closeContract(1L, buyer.getId());



        assertFalse(closeContract.isActive());
        assertEquals(buyer.getId(), closeContract.getItem().getOwner().getId());
        assertEquals(0, BigDecimal.valueOf(90.30).compareTo(closeContract.getBuyer().getAccount()));
        assertEquals(0, BigDecimal.valueOf(101).compareTo(closeContract.getSeller().getAccount()) );

    }

    @Test
    void getAllBySellerId(){
        when(contractRepository.getAllBySellerId(any()))
                .thenThrow(new DataAccessException("Cannot connect to database") {});
        assertThrows(DataAccessException.class, () ->  toTest.getAllBySellerId(1L));

    }

    @Test
    void updatePrice(){
        toTest.updatePrice(1L, BigDecimal.valueOf(15));

        verify(contractRepository).updatePrice(eq(1L), eq(BigDecimal.valueOf(15)) );
    }


}