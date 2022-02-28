package com.example.themarket.service.impl;

import com.example.themarket.model.entity.Contract;
import com.example.themarket.model.entity.Item;
import com.example.themarket.model.entity.User;
import com.example.themarket.model.view.AllActiveContractViewModel;
import com.example.themarket.model.view.AllClosedContractViewModel;
import com.example.themarket.model.view.ContractViewModel;
import com.example.themarket.model.view.CreateContractViewModel;
import com.example.themarket.repository.ContractRepository;
import com.example.themarket.service.ContractService;
import com.example.themarket.service.ItemService;
import com.example.themarket.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractServiceImpl implements ContractService {

    private final ContractRepository contractRepository;
    private final ItemService itemService;
    private final UserService userService;
    private final CurrencyRateApiConfiguration currencyApi;
    private final ModelMapper modelMapper;

    public ContractServiceImpl(ContractRepository contractRepository, ItemService itemService, UserService userService, CurrencyRateApiConfiguration currencyApi, ModelMapper modelMapper) {
        this.contractRepository = contractRepository;
        this.itemService = itemService;
        this.userService = userService;
        this.currencyApi = currencyApi;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public CreateContractViewModel createContract(Long itemId, BigDecimal price) {
        Item item = itemService.findById(itemId);

        User itemOwner = item.getOwner();
        Contract contract = new Contract();
        contract.setActive(true)
                .setSeller(itemOwner)
                .setPrice(price)
                .setItem(item);
        contractRepository.save(contract);

        CreateContractViewModel contractViewModel = new CreateContractViewModel();
        return contractViewModel.setActive(true)
                .setPrice(contract.getPrice())
                .setItemId(itemId)
                .setSellerId(itemOwner.getId());
    }

    @Override
    public void updatePrice(Long itemId, BigDecimal price) {

        contractRepository.updatePrice(itemId, price);

    }

    @Override
    public List<AllActiveContractViewModel> getAllActive() {
        return contractRepository.getAllActive()
                .stream()
                .map(contract -> modelMapper.map(contract, AllActiveContractViewModel.class))
                        .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Contract closeContract(Long itemId, Long buyerId) {
        Contract contract = contractRepository.getByItemId(itemId);
        if (contract == null) {
            throw new EntityNotFoundException("Contract with itemId: " + itemId + " was not found");
        }

        if (contract.isActive()) {
            BigDecimal price = contract.getPrice();
            User buyer = userService.getById(buyerId);
            User seller = contract.getSeller();
            BigDecimal currencyRate = currencyApi.currencyRate(buyer.currency, seller.currency);
            BigDecimal buyerAccount = buyer.getAccount().multiply(currencyRate);

            if (!buyer.getId().equals(seller.getId()) && buyerAccount.compareTo(price) >= 0) {
                contract.setBuyer(buyer)
                        .setActive(false);
                 contractRepository.save(contract);

                moneyTransaction(buyerAccount, buyer, seller, price);

                Item item = contract.getItem();
                item.setOwner(buyer);

                itemService.save(item);
            }
        }

        return contract;
    }

    @Override
    public List<AllClosedContractViewModel> getAllClosed(Long itemId, Long sellerId, Long buyerId) {
        return contractRepository.getAllClosed(itemId, sellerId, buyerId)
                .stream()
                .map(contract -> modelMapper.map(contract, AllClosedContractViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ContractViewModel> getAllBySellerId(Long sellerId) {
        return contractRepository.getAllBySellerId(sellerId)
                .stream()
                .map(projection -> modelMapper.map(projection, ContractViewModel.class))
                .collect(Collectors.toList());
    }

    private void moneyTransaction(BigDecimal buyerAccount, User buyer, User seller, BigDecimal price) {
        buyerAccount = buyerAccount.subtract(price);
        BigDecimal currencyRate = currencyApi.currencyRate(seller.currency, buyer.currency);
        buyerAccount = buyerAccount.multiply(currencyRate) ;
        buyer.setAccount(buyerAccount);
        seller.setAccount(seller.getAccount().add(price));
        userService.save(buyer);
        userService.save(seller);
    }
}
