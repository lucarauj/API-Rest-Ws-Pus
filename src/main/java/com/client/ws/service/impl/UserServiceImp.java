package com.client.ws.service.impl;

import com.client.ws.dto.UserDto;
import com.client.ws.exception.BadRequestException;
import com.client.ws.exception.NotFoundException;
import com.client.ws.mapper.UserMapper;
import com.client.ws.model.User;
import com.client.ws.model.UserType;
import com.client.ws.repository.UserRepository;
import com.client.ws.repository.UserTypeRepository;
import com.client.ws.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;

    UserServiceImp(UserRepository userRepository, UserTypeRepository userTypeRepository) {
        this.userRepository = userRepository;
        this.userTypeRepository = userTypeRepository;
    }

    @Override
    public User create(UserDto userDto) {
        if(Objects.nonNull(userDto.getId())) {
            throw new BadRequestException("Id deve ser nulo");
        }

        var userTypeOpt = userTypeRepository.findById(userDto.getUserTypeId());

        if(userTypeOpt.isEmpty()) {
            throw new NotFoundException("userTypeId não encontrado");
        }

        UserType userType = userTypeOpt.get();
        User user = UserMapper.fromDtoToEntity(userDto, userType, null);
        return userRepository.save(user);
    }
}
