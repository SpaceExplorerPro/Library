package com.library.controllers;


import com.library.dto.UserDTO;
import com.library.entities.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @DeleteMapping("/delete/{id}")
    public @ResponseBody void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

    @PatchMapping("/update/{id}")
    public @ResponseBody User updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @GetMapping("/getAll")
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }
}
