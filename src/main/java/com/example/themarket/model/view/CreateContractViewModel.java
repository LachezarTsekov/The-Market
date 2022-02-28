package com.example.themarket.model.view;

import java.math.BigDecimal;

public class CreateContractViewModel {
    private Long sellerId;
    private Long itemId;
    private BigDecimal price;
    private boolean active;

    public Long getItemId() {
        return itemId;
    }

    public CreateContractViewModel setItemId(Long itemId) {
        this.itemId = itemId;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CreateContractViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isActive() {
        return active;
    }

    public CreateContractViewModel setActive(boolean active) {
        this.active = active;
        return this;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public CreateContractViewModel setSellerId(Long sellerId) {
        this.sellerId = sellerId;
        return this;
    }
}
