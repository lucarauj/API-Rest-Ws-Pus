package com.client.ws.mapper.wsraspay;

import com.client.ws.dto.PaymentProcessDto;
import com.client.ws.dto.wsraspay.OrderDto;

public class OrderMapper {

    public static OrderDto build(String customerId, PaymentProcessDto paymentProcessDto) {
        return OrderDto.builder()
                .customerId(customerId)
                .discount(paymentProcessDto.getDiscount())
                .productAcronym(paymentProcessDto.getProductKey())
                .build();
    }
}
