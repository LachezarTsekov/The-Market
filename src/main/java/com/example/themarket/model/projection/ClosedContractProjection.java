package com.example.themarket.model.projection;

import java.math.BigDecimal;

public interface ClosedContractProjection {
    Long getSellerId();
    String getSellerUsername();
    Long getBuyerId();
    String getBuyerUsername();
    Long getItemId();
    BigDecimal getPrice();
    boolean getActive();
}
