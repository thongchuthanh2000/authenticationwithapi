package com.fpt.authenticationwithapi.controller;

import com.fpt.authenticationwithapi.exception.UserNotFoundException;
import com.fpt.authenticationwithapi.model.Role;
import com.fpt.authenticationwithapi.model.User;
import com.fpt.authenticationwithapi.service.RoleService;
import com.fpt.authenticationwithapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/users/")
    public List<User> getAllUsers() {
            return userService.getAll();
    }

    @GetMapping("/users/search/{searchName}")
    public List<User> getAllByUsers(@PathVariable(value = "searchName") String searchName) {
        return userService.getByName(searchName);
    }

    @GetMapping("/users/{id}")
    public User getAllUsers(@PathVariable(value = "id") Integer id) {
        User user = userService.get(id);
        if (user == null) {
            throw new UserNotFoundException("User not found by id = " + id);
        } else {
            return user;
        }
    }

    @PostMapping("/users")
    public void createUser(@RequestBody User user) {
        Set<Role> roles = new HashSet<>();
        for (Role roleInput : user.getRoles()) {
            Role role = roleService.getByName(roleInput.getName());
            roles.add(role);
        }
        user.setRoles(roles);
        userService.save(user);
    }

    @PutMapping("/users")
    public void updateUser(@RequestBody User userInput) {
        User user = userService.get(userInput.getId());
        if (user == null) {
            throw new UserNotFoundException("User not found by id = " + userInput.getId());
        } else {
            user.setName(userInput.getName());
            user.setLogin(userInput.getLogin());
            user.setPassword(userInput.getPassword());
            Set<Role> roles = new HashSet<>();
            for (Role roleInput : userInput.getRoles()) {
                Role role = roleService.getByName(roleInput.getName());
                roles.add(role);
            }
            user.setRoles(roles);

            userService.update(user);
        }
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable(value = "id") Integer id) {
        User user = userService.get(id);
        if (user == null) {
            throw new UserNotFoundException("User not found by id = " + id);
        } else {
            userService.delete(user);
        }
    }

}
