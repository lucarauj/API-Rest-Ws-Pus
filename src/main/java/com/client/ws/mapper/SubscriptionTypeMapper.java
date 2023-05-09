package com.client.ws.mapper;

import com.client.ws.dto.SubscriptionTypeDto;
import com.client.ws.model.SubscriptionType;

public class SubscriptionTypeMapper {

    public static SubscriptionType fromDtoToEntity(SubscriptionTypeDto subscriptionTypeDto) {
        return SubscriptionType.builder()
                .id(subscriptionTypeDto.getId())
                .name(subscriptionTypeDto.getName())
                .accessMonths(subscriptionTypeDto.getAccessMonths())
                .price(subscriptionTypeDto.getPrice())
                .productKey(subscriptionTypeDto.getProductKey())
                .build();
    }
}
