package com.client.ws.integration;

import com.client.ws.dto.wsraspay.CreditCardDto;
import com.client.ws.dto.wsraspay.CustomerDto;
import com.client.ws.dto.wsraspay.OrderDto;
import com.client.ws.dto.wsraspay.PaymentDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
class WsRaspayIntegrationImplTest {

    @Autowired
    private WsRaspayIntegration wsRaspayIntegration;

    @Test
    void createCustomerWhenDtoOk() {
        CustomerDto customerDto = new CustomerDto("3", "67189624066", "kokikos215@jobbrett.com", "Lucas", "Araujo");
        wsRaspayIntegration.createCustomer(customerDto);
    }

    @Test
    void createOrderWhenDtoOk() {
        OrderDto orderDto = new OrderDto("1", "3", BigDecimal.ZERO, "MONTH22");
        wsRaspayIntegration.createOrder(orderDto);
    }

    @Test
    void processPaymentWhenDtoOk() {
        CreditCardDto creditCardDto = new CreditCardDto(123L, "85773383047", 0L, 06L, "6464656567676869", 2026L);
        PaymentDto paymentDto = new PaymentDto(creditCardDto, "3", "1");
        wsRaspayIntegration.processPayment(paymentDto);
    }
}
