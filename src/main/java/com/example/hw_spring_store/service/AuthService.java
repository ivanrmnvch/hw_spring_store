package com.example.hw_spring_store.service;

import com.example.hw_spring_store.dto.*;
import com.example.hw_spring_store.entities.User;
import com.example.hw_spring_store.exceptions.Resp;
import com.example.hw_spring_store.utils.JwtTokenUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserService userService;
  private final JwtTokenUtils jwtTokenUtils;
  private final AuthenticationManager authenticationManager;

  public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
    // сделать глобальный перехват исключений
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getLogin(), authRequest.getPassword()));
    } catch (BadCredentialsException e) {
      return new ResponseEntity<>(new Resp(HttpStatus.UNAUTHORIZED.value(), "Неверный логин или пароль"), HttpStatus.UNAUTHORIZED);
    }

    UserDetails userDetails = userService.loadUserByUsername(authRequest.getLogin());
    String token = jwtTokenUtils.generateToken(userDetails);
    return ResponseEntity.ok(new AuthorizedUserDto(token, authRequest.getLogin()));
  }

  public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
    if (userService.fingByLogin(registrationUserDto.getLogin()).isPresent()) {
      return new ResponseEntity<>(new Resp(HttpStatus.BAD_REQUEST.value(), "Пользователь с указаным логином уже зарегистрирован"), HttpStatus.BAD_REQUEST);
    }
    User user = userService.createNewUser(registrationUserDto);
    return ResponseEntity.ok(new UserDto(user.getId(), user.getLogin(), user.getEmail()));
  }
}
