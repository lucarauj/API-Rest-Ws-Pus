package com.client.ws.mapper.wsraspay;

import com.client.ws.dto.wsraspay.CreditCardDto;
import com.client.ws.dto.wsraspay.PaymentDto;

public class PaymentMapper {

    public static PaymentDto build(String customerId, String orderId, CreditCardDto creditCardDto) {
        return PaymentDto.builder()
                .creditCard(creditCardDto)
                .customerId(customerId)
                .orderId(orderId)
                .build();
    }
}
