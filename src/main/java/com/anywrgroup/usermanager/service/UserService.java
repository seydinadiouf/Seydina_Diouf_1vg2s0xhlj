package com.anywrgroup.usermanager.service;

import com.anywrgroup.usermanager.dto.UserDTO;
import com.anywrgroup.usermanager.dto.request.SignInRequest;
import com.anywrgroup.usermanager.dto.response.SignInResponse;
import com.anywrgroup.usermanager.exceptions.UnexpectedErrorException;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);

    UserDTO readUserByUsername(String username);

    SignInResponse signIn(SignInRequest signInRequest) throws UnexpectedErrorException;

}
