package com.example.hw_spring_store.controller;

import com.example.hw_spring_store.dto.JwtRequest;
import com.example.hw_spring_store.dto.RegistrationUserDto;
import com.example.hw_spring_store.dto.TokenDto;
import com.example.hw_spring_store.service.AuthService;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
  private final AuthService authService;

  @PostMapping("/auth")
  public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
   return authService.createAuthToken(authRequest);
  }

  @PostMapping("/registration")
  public ResponseEntity<?> createNewUser(@RequestBody RegistrationUserDto registrationUserDto) {
   return authService.createNewUser(registrationUserDto);
  }

  @RequestMapping(value = "/refresh", method = RequestMethod.POST, produces = "application/json")
  public ResponseEntity<?> refreshToken(@RequestBody TokenDto tokenDto) {
    return authService.refreshToken(tokenDto);
  }
}
