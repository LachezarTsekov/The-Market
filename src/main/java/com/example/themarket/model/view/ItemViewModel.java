package com.example.themarket.model.view;

public class ItemViewModel {

    private Long id;
    private String name;
    private Long ownerId;
    private String ownerUsername;

    public ItemViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ItemViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ItemViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public ItemViewModel setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public String getOwnerUsername() {
        return ownerUsername;
    }

    public ItemViewModel setOwnerUsername(String ownerUsername) {
        this.ownerUsername = ownerUsername;
        return this;
    }
}
