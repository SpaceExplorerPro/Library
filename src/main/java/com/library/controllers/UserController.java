/**
 * Controller class responsible for handling user-related operations.
 */
package com.library.controllers;

import com.library.dto.UserDTO;
import com.library.entities.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing user-related endpoints.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * Constructs a UserController with the provided UserService.
     *
     * @param userService The service responsible for user-related operations.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for adding a new user to the system.
     *
     * @param user The User object to be added.
     * @return The added User object.
     */
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    /**
     * Endpoint for deleting a user from the system by their ID.
     *
     * @param id The ID of the user to be deleted.
     */
    @DeleteMapping("/delete/{id}")
    public @ResponseBody void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    /**
     * Endpoint for updating information of a user in the system.
     *
     * @param id          The ID of the user to be updated.
     * @param updatedUser The updated User object.
     * @return The updated User object.
     */
    @PatchMapping("/update/{id}")
    public @ResponseBody User updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    /**
     * Endpoint for retrieving all users in the system.
     *
     * @return A list of all UserDTO objects in the system.
     */
    @GetMapping("/getAll")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
