package com.example.themarket.model.projection;

import java.math.BigDecimal;

public interface ContractBySellerProjection {
    Long getContractId();
    Long getSellerId();
    Long getItemId();
    Long getBuyerId();
    String getBuyerUsername();
    String getSellerUsername();
    BigDecimal getPrice();
    boolean getActive();


}
