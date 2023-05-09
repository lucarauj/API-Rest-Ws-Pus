package com.client.ws.service;

import com.client.ws.dto.PaymentProcessDto;

public interface PaymentInfoService {

    Boolean process(PaymentProcessDto paymentProcessDto);
}
