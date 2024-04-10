package com.library.service;

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
        validateRegistration(request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request);

        String token = jwtService.generateToken(request);

        return new AuthenticationResponse(token);

//        User user = new User();
//        user.setFullName(request.getFullName());
//        user.setUsername(request.getUsername());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
//        user.setRole(request.getRole());
//        user.setEmail(request.getEmail());
//        user.setLoans(request.getLoans());
    }

    private void validateRegistration(User request) {
        DataValidation.validateUser(request, userRepository);
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

