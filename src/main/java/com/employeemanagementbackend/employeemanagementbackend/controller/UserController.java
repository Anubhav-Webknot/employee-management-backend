package com.employeemanagementbackend.employeemanagementbackend.controller;

import com.employeemanagementbackend.employeemanagementbackend.exception.UserNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.Users;
import com.employeemanagementbackend.employeemanagementbackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/users")
    public Users saveUsers(@RequestBody Users users)
    {

        return usersService.saveUsers(users);
    }

    @GetMapping("/users")
    public List<Users> getAllUsers(){

        return usersService.fetchAllUsers();
    }

    @GetMapping("/users/{id}")
    public Users getUsersById(@PathVariable("id") Long id) throws UserNotFoundException {

        return usersService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public Users updateUserById(@PathVariable("id") Long id, Users user) throws UserNotFoundException {

        return usersService.updateUser(id,user);
    }


    @DeleteMapping("/users/{id}")
    public String deleteUsers(@PathVariable("id") Long id) throws UserNotFoundException{

        return usersService.deleteUsersById(id);
    }


}
