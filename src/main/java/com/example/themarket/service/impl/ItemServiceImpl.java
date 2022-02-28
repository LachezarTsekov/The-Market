package com.example.themarket.service.impl;

import com.example.themarket.model.binding.ItemCreateBindingModel;
import com.example.themarket.model.entity.Item;
import com.example.themarket.model.entity.User;
import com.example.themarket.model.view.ItemViewModel;
import com.example.themarket.repository.ItemRepository;
import com.example.themarket.service.ItemService;
import com.example.themarket.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;

    public ItemServiceImpl(ItemRepository itemRepository, ModelMapper modelMapper,@Lazy UserService userService) {
        this.itemRepository = itemRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }



    @Override
    public ItemViewModel createItem(ItemCreateBindingModel item) {

            User owner = userService.getById(item.getOwnerId());



           Item newItem = new Item();

           newItem.setName(item.getName())
                   .setOwner(owner);
          newItem = itemRepository.save(newItem);

           return new ItemViewModel()
                   .setOwnerId(owner.getId())
                   .setOwnerUsername(owner.getUsername())
                   .setId(newItem.getId())
                   .setName(newItem.getName());



    }

    @Override
    public List<ItemViewModel> getAllByOwnerId(Long id) {
        return itemRepository.getAllByOwnerId(id)
                .stream()
                .map(itemProjection -> modelMapper.map(itemProjection, ItemViewModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public Item findById(Long itemId) {
        return itemRepository.findById(itemId)
                .orElseThrow(() -> new EntityNotFoundException("Item with id: " + itemId + " was not found"));
    }

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }


}
