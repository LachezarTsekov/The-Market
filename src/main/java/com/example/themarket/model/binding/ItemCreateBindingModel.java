package com.example.themarket.model.binding;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class ItemCreateBindingModel {

    private String name;
    @Positive
    private Long ownerId;

    public ItemCreateBindingModel() {
    }

    public String getName() {
        return name;
    }

    public ItemCreateBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public ItemCreateBindingModel setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }
}
