package com.example.fulllogin.service;

import com.example.fulllogin.repository.UserRepository;
import com.example.fulllogin.user.User;
import com.example.fulllogin.vo.AuthenticationRequest;
import com.example.fulllogin.vo.AuthenticationResponse;
import com.example.fulllogin.vo.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;
  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    User user = User.convertToUser(request,
        passwordEncoder.encode(request.getPassword()));
    repository.save(user);
    String token = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(token)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    String token = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
        .token(token)
        .build();
  }
}
