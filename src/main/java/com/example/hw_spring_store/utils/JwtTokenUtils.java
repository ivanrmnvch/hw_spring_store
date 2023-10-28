package com.example.hw_spring_store.utils;

import com.example.hw_spring_store.dto.JwtRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenUtils {
  @Value(
    "${jwt.secret}"
  )
  private String secret;

  @Value(
    "${jwt.lifetime}"
  )
  private Duration lifetime;

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    List<String> rolesList = userDetails.getAuthorities().stream()
      .map(GrantedAuthority::getAuthority)
      .collect(Collectors.toList());
    // добавить поле в payload
    claims.put("roles", rolesList);
//    claims.put("email", )

    // дата создания токена
    Date issuedDate = new Date();
    // дата смерти токена

    System.out.println("1");

    Date expiredDate = new Date(issuedDate.getTime() + lifetime.toMillis());

    System.out.println("2");

    try {
      return Jwts.builder()
        .setClaims(claims)
        .setSubject(userDetails.getUsername())
        .setExpiration(expiredDate)
        .signWith(SignatureAlgorithm.HS256, secret)
        .compact();
    } catch (Exception e) {
      System.out.println("TEST");
      System.err.println(e);
    }
    return "test";
  }

  public String getLogin(String token) {
    return getAllClaimsFromToken(token).getSubject();
  }

  public List<String> getRoles(String token) {
    return getAllClaimsFromToken(token).get("roles", List.class);
  }

  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
      .setSigningKey(secret)
      .parseClaimsJws(token)
      .getBody();
  }
}


