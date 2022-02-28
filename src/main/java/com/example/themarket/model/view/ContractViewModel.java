package com.example.themarket.model.view;

import java.math.BigDecimal;

public class ContractViewModel {

    private Long contractId;
    private Long sellerId;
    private String sellerUsername;
    private Long buyerId;
    private String buyerUsername;
    private Long itemId;
    private BigDecimal price;
    private boolean active;

    public ContractViewModel() {
    }

    public Long getSellerId() {
        return sellerId;
    }

    public ContractViewModel setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public ContractViewModel setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ContractViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public ContractViewModel setActive(boolean active) {
        this.active = active;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public ContractViewModel setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public ContractViewModel setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public ContractViewModel setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
        return this;
    }

    public Long getContractId() {
        return contractId;
    }

    public ContractViewModel setContractId(Long contractId) {
        this.contractId = contractId;
        return this;
    }
}
