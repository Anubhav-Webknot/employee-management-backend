package com.employeemanagementbackend.employeemanagementbackend.service;


import com.employeemanagementbackend.employeemanagementbackend.exception.UserNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.usersModel;
import com.employeemanagementbackend.employeemanagementbackend.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;


    public usersModel saveUsers(usersModel users){



        return usersRepository.save(users);
    }

    public List<usersModel> fetchAllUsers(){
        List<usersModel> allUsers = usersRepository.findAll();
        return allUsers;
    }

    public usersModel getUserById(Long id)throws UserNotFoundException {
        Optional<usersModel> users = usersRepository.findById(id);
        if (users.isPresent()) {
            return users.get();
        }
        return null;
    }


    public String deleteUsersById(Long id) throws UserNotFoundException {
        if (usersRepository.findById(id).isPresent()) {
            usersRepository.deleteById(id);
            return "User deleted successfully";
        }
        return "No such user in the database";
    }


}