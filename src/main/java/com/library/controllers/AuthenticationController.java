/**
 * Controller class handling user authentication endpoints.
 */
package com.library.controllers;

import com.library.security.AuthenticationResponse;
import com.library.entities.User;
import com.library.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for handling user authentication operations such as registration and login.
 */
@RestController
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    /**
     * Constructs an AuthenticationController with the provided AuthenticationService.
     *
     * @param authenticationService The service responsible for user authentication.
     */
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Endpoint for user registration.
     *
     * @param request The User object containing registration information.
     * @return ResponseEntity containing the authentication response.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    /**
     * Endpoint for user login.
     *
     * @param request The User object containing login credentials.
     * @return ResponseEntity containing the authentication response.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
