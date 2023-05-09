package com.client.ws.mapper.wsraspay;

import com.client.ws.dto.UserPaymentInfoDto;
import com.client.ws.dto.wsraspay.CreditCardDto;

public class CreditCardMapper {

    public static CreditCardDto build(UserPaymentInfoDto userPaymentInfoDto, String documentNumber) {
        return CreditCardDto.builder()
                .documentNumber(documentNumber)
                .cvv(Long.parseLong(userPaymentInfoDto.getCardSecurityCode()))
                .installments(userPaymentInfoDto.getInstallments())
                .month(userPaymentInfoDto.getCardExpirationMonth())
                .year(userPaymentInfoDto.getCardExpirationYear())
                .number(userPaymentInfoDto.getCardNumber())
                .build();
    }
}
