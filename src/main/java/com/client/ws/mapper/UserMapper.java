package com.client.ws.mapper;

import com.client.ws.dto.UserDto;
import com.client.ws.model.SubscriptionType;
import com.client.ws.model.User;
import com.client.ws.model.UserType;

public class UserMapper {

    public static User fromDtoToEntity(UserDto userDto, UserType userType, SubscriptionType subscriptionType) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .cpf(userDto.getCpf())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .dtSubscription(userDto.getDtSubscription())
                .dtExpiration(userDto.getDtExpiration())
                .userType(userType)
                .subscriptionType(subscriptionType)
                .build();
    }
}
