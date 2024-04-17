/**
 * Service class for user authentication and registration.
 */
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

/**
 * Service class for managing user authentication and registration.
 */
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

    /**
     * Registers a new user.
     *
     * @param request The user registration request.
     * @return An AuthenticationResponse containing the authentication token.
     * @throws ElementAlreadyExistsException if a user with the same username already exists.
     */
    public AuthenticationResponse register(User request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent())
            throw new ElementAlreadyExistsException("User already exists");
        validateRegistration(request);
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(request);

        String token = jwtService.generateToken(request);

        return new AuthenticationResponse(token);
    }

    /**
     * Validates user registration data.
     *
     * @param request The user registration request to validate.
     */
    private void validateRegistration(User request) {
        DataValidation.validateUser(request);
    }

    /**
     * Authenticates a user.
     *
     * @param request The user authentication request.
     * @return An AuthenticationResponse containing the authentication token.
     */
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
