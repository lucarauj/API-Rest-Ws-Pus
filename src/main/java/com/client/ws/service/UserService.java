package com.client.ws.service;

import com.client.ws.dto.UserDto;
import com.client.ws.model.User;

public interface UserService {

    User create(UserDto userDto);

}
