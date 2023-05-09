package com.client.ws.integration;

import com.client.ws.dto.wsraspay.CustomerDto;
import com.client.ws.dto.wsraspay.OrderDto;
import com.client.ws.dto.wsraspay.PaymentDto;

public interface WsRaspayIntegration {

    CustomerDto createCustomer(CustomerDto dto);

    OrderDto createOrder(OrderDto dto);

    Boolean processPayment(PaymentDto dto);
}
