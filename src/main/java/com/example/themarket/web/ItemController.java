package com.example.themarket.web;

import com.example.themarket.model.binding.ItemCreateBindingModel;
import com.example.themarket.model.view.ItemViewModel;
import com.example.themarket.service.ItemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"Item Controller"})
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "Create a item with following parameters: name, userId(owner)",
    notes = "ownerId must be positive")
    @PostMapping("/items/create")
    public ItemViewModel createItem(@RequestBody ItemCreateBindingModel item){
         return itemService.createItem(item);
    }

    @ApiOperation(value = "Return collection with items owned by selected user")
    @GetMapping("/items/get-all-by-owner-id")
    public List<ItemViewModel> getAllByOwnerId(@RequestParam Long id){

        return itemService.getAllByOwnerId(id);
    }


}
