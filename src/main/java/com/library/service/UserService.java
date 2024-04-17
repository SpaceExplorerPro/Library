/**
 * Service class for managing users.
 */
package com.library.service;

import com.library.dto.UserDTO;
import com.library.entities.User;
import com.library.exceptions.ElementAlreadyExistsException;
import com.library.exceptions.ElementNotFoundException;
import com.library.exceptions.RegistrationException;
import com.library.other.DataValidation;
import com.library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for performing operations related to users.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Adds a new user to the system.
     *
     * @param user The user to add.
     * @return The added user.
     * @throws ElementAlreadyExistsException if a user with the same username already exists.
     */
    public User addUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent())
            throw new ElementAlreadyExistsException("User already exists");
        validateUser(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /**
     * Deletes a user from the system.
     *
     * @param id The ID of the user to delete.
     * @throws ElementNotFoundException if the user with the given ID is not found.
     */
    public void deleteUser(Integer id) {
        if (userRepository.findById(id).isEmpty()) throw new ElementNotFoundException("User not found");
        userRepository.deleteById(id);
    }

    /**
     * Updates the information of a user.
     *
     * @param id          The ID of the user to update.
     * @param updatedUser The updated user information.
     * @return The updated user.
     * @throws ElementNotFoundException   if the user with the given ID is not found.
     * @throws RegistrationException       if the updated user's username already exists in the database.
     */
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

    /**
     * Retrieves all users from the system.
     *
     * @return A list of UserDTO objects representing all users.
     */
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Validates the data of a user.
     *
     * @param user The user to validate.
     */
    private void validateUser(User user) {
        DataValidation.validateUser(user);
    }

    /**
     * Converts a User entity to a UserDTO object.
     *
     * @param user The User entity to convert.
     * @return A UserDTO object representing the User entity.
     */
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
