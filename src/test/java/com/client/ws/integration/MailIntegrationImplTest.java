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
class MailIntegrationImplTest {

    @Autowired
    private MailIntegration mailIntegration;

    @Test
    void sendMail() {

        mailIntegration.send("lukas.martins193@gmail.com", "Olá, Aluno!", "Acesso liberado!");
    }
}
