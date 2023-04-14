package com.anywrgroup.usermanager.service.impl;

import com.anywrgroup.usermanager.dto.Role;
import com.anywrgroup.usermanager.dto.UserDTO;
import com.anywrgroup.usermanager.dto.request.SignInRequest;
import com.anywrgroup.usermanager.dto.response.SignInResponse;
import com.anywrgroup.usermanager.entity.User;
import com.anywrgroup.usermanager.exceptions.ForbiddenActionException;
import com.anywrgroup.usermanager.exceptions.ResourceNotFoundException;
import com.anywrgroup.usermanager.exceptions.UnexpectedErrorException;
import com.anywrgroup.usermanager.mapper.UserMapper;
import com.anywrgroup.usermanager.repository.UserRepository;
import com.anywrgroup.usermanager.security.jwt.JwtUtils;
import com.anywrgroup.usermanager.security.model.UserDetailsImpl;
import com.anywrgroup.usermanager.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class UserServiceImpl implements UserService {

    private static final String USER_ALREADY_EXIST = "User with this email or username already exist";
    private static final String USER_NOT_FOUND = "User with id {0} not found";

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final JwtUtils jwtUtils;

    public UserServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, PasswordEncoder passwordEncoder, UserMapper userMapper, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new ForbiddenActionException(USER_ALREADY_EXIST);
        }
        User user = userMapper.toEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDTO readUserByUsername(String username) {
        return userRepository.findByUsername(username).map(userMapper::toDto).orElseThrow(()-> new ResourceNotFoundException(MessageFormat.format(USER_NOT_FOUND, username)) );
    }

    @Override
    public SignInResponse signIn(SignInRequest signInRequest) throws UnexpectedErrorException {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Role role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst().map(Role::valueOf).orElseThrow(() -> new UnexpectedErrorException("Unexpected error occurs"));

        return new SignInResponse(signInRequest.getUsername(), jwt, role);

    }


}
