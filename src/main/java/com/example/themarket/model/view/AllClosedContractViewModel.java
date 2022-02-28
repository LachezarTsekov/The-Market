package com.example.themarket.model.view;

import java.math.BigDecimal;

public class AllClosedContractViewModel {

    private Long sellerId;
    private String sellerUsername;
    private Long buyerId;
    private String buyerUsername;
    private Long itemId;
    private BigDecimal price;
    private boolean active;

    public Long getSellerId() {
        return sellerId;
    }

    public AllClosedContractViewModel setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public AllClosedContractViewModel setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public AllClosedContractViewModel setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
        return this;
    }

    public String getBuyerUsername() {
        return buyerUsername;
    }

    public AllClosedContractViewModel setBuyerUsername(String buyerUsername) {
        this.buyerUsername = buyerUsername;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public AllClosedContractViewModel setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AllClosedContractViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public AllClosedContractViewModel setActive(boolean active) {
        this.active = active;
        return this;
    }
}
