package com.anywrgroup.usermanager.controller;

import com.anywrgroup.usermanager.dto.UserDTO;
import com.anywrgroup.usermanager.dto.request.SignInRequest;
import com.anywrgroup.usermanager.dto.response.SignInResponse;
import com.anywrgroup.usermanager.exceptions.UnexpectedErrorException;
import com.anywrgroup.usermanager.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.createUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/signIn")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) throws UnexpectedErrorException {
        SignInResponse signInResponse = userService.signIn(signInRequest);
        return new ResponseEntity<>(signInResponse, HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String username) {
        UserDTO userDTO = userService.readUserByUsername(username);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
