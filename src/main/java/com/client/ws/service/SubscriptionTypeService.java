package com.client.ws.service;

import com.client.ws.dto.SubscriptionTypeDto;
import com.client.ws.model.SubscriptionType;

import java.util.List;

public interface SubscriptionTypeService {

    List<SubscriptionType> findAll();

    SubscriptionType findById(Long id);

    SubscriptionType create(SubscriptionTypeDto subscriptionTypeDto);

    SubscriptionType update(Long id, SubscriptionTypeDto subscriptionTypeDto);

    void delete(Long id);
}
