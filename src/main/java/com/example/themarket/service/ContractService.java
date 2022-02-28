package com.example.themarket.service;

import com.example.themarket.model.entity.Contract;
import com.example.themarket.model.view.AllActiveContractViewModel;
import com.example.themarket.model.view.AllClosedContractViewModel;
import com.example.themarket.model.view.ContractViewModel;
import com.example.themarket.model.view.CreateContractViewModel;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ContractService {
    CreateContractViewModel createContract(Long itemId, BigDecimal price);

    void updatePrice(Long itemId, BigDecimal price);

    List<AllActiveContractViewModel> getAllActive();

    Contract closeContract(Long itemId, Long buyerId) throws IOException;

    List<AllClosedContractViewModel> getAllClosed(Long itemId, Long sellerId, Long buyerId);

    List<ContractViewModel> getAllBySellerId(Long sellerId);
}
