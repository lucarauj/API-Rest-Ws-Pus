package com.client.ws.service.impl;

import com.client.ws.model.UserType;
import com.client.ws.repository.UserTypeRepository;
import com.client.ws.service.UserTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    private final UserTypeRepository userTypeRepository;

    UserTypeServiceImpl(UserTypeRepository userTypeRepository) {
        this.userTypeRepository = userTypeRepository;
    }
    @Override
    public List<UserType> findAll() {
        return userTypeRepository.findAll();
    }
}
