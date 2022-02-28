package com.example.themarket.model.projection;

import java.math.BigDecimal;

public interface ActiveContractProjection {
    Long getSellerId();
    String getSellerUsername();
    Long getItemId();
    BigDecimal getPrice();
    boolean getActive();
    void setActive(boolean active);
}
