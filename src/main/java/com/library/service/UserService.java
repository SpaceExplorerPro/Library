package com.library.service;


import com.library.exceptions.ElementAlreadyExistsException;
import com.library.exceptions.RegistrationException;
import com.library.other.DataValidation;
import com.library.dto.UserDTO;
import com.library.entities.User;
import com.library.repositories.UserRepository;
import com.library.exceptions.ElementNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User addUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new ElementAlreadyExistsException("User already exists");
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteUser(Integer id) {
        if (userRepository.findById(id).isEmpty()) throw new ElementNotFoundException("User not found");
        userRepository.deleteById(id);
    }

    public User updateUser(Integer id, User updatedUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("User not found"));

        if (!existingUser.getUsername().equals(updatedUser.getUsername())
                && userRepository.findByUsername(updatedUser.getUsername()).isPresent())
            throw new RegistrationException("Username already exists in database");

        validateUser(updatedUser);

        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        existingUser.setRole(updatedUser.getRole());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setFullName(updatedUser.getFullName());

        return userRepository.save(existingUser);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private void validateUser(User user) {
        DataValidation.validateUser(user);
    }

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setRole(user.getRole());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getFullName());
        return userDTO;
    }
}