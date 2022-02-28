package com.example.themarket.model.view;

import java.math.BigDecimal;

public class AllActiveContractViewModel {

    private Long sellerId;
    private String sellerUsername;
    private Long itemId;
    private BigDecimal price;
    private boolean active;

    public Long getSellerId() {
        return sellerId;
    }

    public AllActiveContractViewModel setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    public AllActiveContractViewModel setSellerUsername(String sellerUsername) {
        this.sellerUsername = sellerUsername;
        return this;
    }

    public Long getItemId() {
        return itemId;
    }

    public AllActiveContractViewModel setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AllActiveContractViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public AllActiveContractViewModel setActive(boolean active) {
        this.active = active;
        return this;
    }
}
