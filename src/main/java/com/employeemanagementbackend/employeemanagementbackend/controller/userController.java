package com.employeemanagementbackend.employeemanagementbackend.controller;

import com.employeemanagementbackend.employeemanagementbackend.exception.UserNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.employeeModel;
import com.employeemanagementbackend.employeemanagementbackend.model.usersModel;
import com.employeemanagementbackend.employeemanagementbackend.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class userController {

    @Autowired
    private UsersService usersService;

    @PostMapping("/users")
    public usersModel saveUsers(@RequestBody usersModel users)
    {

        return usersService.saveUsers(users);
    }

    @GetMapping("/users")
    public List<usersModel> getAllUsers(){

        return usersService.fetchAllUsers();
    }

    @GetMapping("/users/{id}")
    public usersModel getUsersById(@PathVariable("id") Long id) throws UserNotFoundException {

        return usersService.getUserById(id);
    }

    @PutMapping("/users/{id}")
    public usersModel updateUserById(@PathVariable("id") Long id,usersModel user) throws UserNotFoundException {

        return usersService.updateUser(id,user);
    }


    @DeleteMapping("/users/{id}")
    public String deleteUsers(@PathVariable("id") Long id) throws UserNotFoundException{

        return usersService.deleteUsersById(id);
    }


}
