package com.example.themarket.web;

import com.example.themarket.model.entity.Contract;
import com.example.themarket.model.view.AllActiveContractViewModel;
import com.example.themarket.model.view.AllClosedContractViewModel;
import com.example.themarket.model.view.ContractViewModel;
import com.example.themarket.model.view.CreateContractViewModel;
import com.example.themarket.service.ContractService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@Api(tags = {"Contract Controller"})
public class ContractController {

    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @ApiOperation(value = "Create a contract with following parameters: itemId, price")
    @PostMapping("/contract/create")
    public CreateContractViewModel createContract(@RequestParam @Min(1) Long itemId,
                                                  @RequestParam @Min(0) BigDecimal price){
        return contractService.createContract(itemId, price);
    }

    @ApiOperation(value = "Update the price of selected contract by itemId")
    @PatchMapping("/contract/update")
    public void updatePrice(@RequestParam Long itemId, @RequestParam BigDecimal price){
        contractService.updatePrice(itemId, price);
    }

    @ApiOperation(value = "Return collection of all active contracts ")
    @GetMapping("/contract/get-all-active")
    public List<AllActiveContractViewModel> getAllActive(){
       return contractService.getAllActive();
    }

    @ApiOperation(value = "Close selected contract by itemId")
    @PatchMapping("/contract/closing")
    public Contract closing(@RequestParam Long itemId, @RequestParam Long buyerId) throws IOException {
       return contractService.closeContract(itemId, buyerId);
    }

    @ApiOperation(value = "Return collection of closed contracts selected by optional parameters")
    @GetMapping("/contract/get-all-closed")
    public List<AllClosedContractViewModel> getAllClosed(@RequestParam(required = false) Long itemId,
                                                                           @RequestParam(required = false) Long sellerId,
                                                                           @RequestParam(required = false) Long buyerId){
        return contractService.getAllClosed(itemId, sellerId, buyerId);
    }

    @ApiOperation(value = "Return collection of contracts by seller")
    @GetMapping("/contract/get-all-by-seller-id")
    public List<ContractViewModel> getAllBySellerId(@RequestParam Long sellerId){
        return contractService.getAllBySellerId(sellerId);
    }
}
