package com.example.themarket.service;

import com.example.themarket.model.binding.ItemCreateBindingModel;
import com.example.themarket.model.entity.Item;
import com.example.themarket.model.view.ItemViewModel;

import java.util.List;

public interface ItemService {
    ItemViewModel createItem(ItemCreateBindingModel item);

    List<ItemViewModel> getAllByOwnerId(Long id);

    Item findById(Long itemId);

    void save(Item item);
}
