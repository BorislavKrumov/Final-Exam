package com.academy.supermarket.model.util;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class PurchaseParams {
    @NotBlank(message = "The supermarket Id should not be empty or blank!")
    String superMarketId;

    @NotEmpty
    List<String> itemIds;

    @NotBlank
    @NotEmpty(message = "Payment type should be CARD or CASH")
    String paymentType;

    private double cashAmount;
}
