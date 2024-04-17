package com.library.service;

import com.library.exceptions.ElementAlreadyExistsException;
import com.library.other.DataValidation;
import com.library.entities.User;
import com.library.repositories.UserRepository;
import com.library.security.AuthenticationResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse register(User request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent())
            throw new ElementAlreadyExistsException("User already exists");
        validateRegistration(request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request);

        String token = jwtService.generateToken(request);

        return new AuthenticationResponse(token);
    }

    private void validateRegistration(User request) {
        DataValidation.validateUser(request);
    }

    public AuthenticationResponse authenticate(User request) {
        authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                        )
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
}

