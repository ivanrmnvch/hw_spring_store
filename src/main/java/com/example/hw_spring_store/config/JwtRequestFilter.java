package com.example.hw_spring_store.config;

import com.example.hw_spring_store.utils.JwtTokenUtils;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter {
  private final JwtTokenUtils jwtTokenUtils;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String authHeader = request.getHeader("Authorization");
    String login = null;
    String jwt = null;

    if (authHeader != null && authHeader.startsWith("Bearer")) {
      jwt = authHeader.substring(7);
      try {
        login = jwtTokenUtils.getLogin(jwt);
      } catch (ExpiredJwtException e) {
        log.debug("Время жизни токена вышло");
      } catch (SignatureException e) {
        log.debug("Подпись не действительна");
      }
    }

    if (login != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
        login,
        null,
        jwtTokenUtils.getRoles(jwt).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList())
      );
      SecurityContextHolder.getContext().setAuthentication(token);
      System.out.println("положил пользователя в контест");
    }
    filterChain.doFilter(request, response);
  }
}
