package com.client.ws.mapper;

import com.client.ws.dto.UserPaymentInfoDto;
import com.client.ws.model.User;
import com.client.ws.model.UserPaymentInfo;

public class UserPaymentInfoMapper {

    public static UserPaymentInfo fromDtoToEntity(UserPaymentInfoDto userPaymentInfoDto, User user) {

        return UserPaymentInfo.builder()
                .id(userPaymentInfoDto.getId())
                .cardNumber(userPaymentInfoDto.getCardNumber())
                .cardExpirationMonth(userPaymentInfoDto.getCardExpirationMonth())
                .cardExpirationYear(userPaymentInfoDto.getCardExpirationYear())
                .cardSecurityCode(userPaymentInfoDto.getCardSecurityCode())
                .price(userPaymentInfoDto.getPrice())
                .dtPayment(userPaymentInfoDto.getDtPayment())
                .user(user)
                .build();
    }
}
