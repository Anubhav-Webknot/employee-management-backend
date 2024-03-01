package com.employeemanagementbackend.employeemanagementbackend.service;


import com.employeemanagementbackend.employeemanagementbackend.exception.UserNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.Users;
import com.employeemanagementbackend.employeemanagementbackend.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j

public class UsersService {

//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersRepository usersRepository;



    public Users saveUsers(Users users){

//        users.setPassword(this.passwordEncoder.encode(users.getPassword()));
        log.info("creating new user");
        return usersRepository.save(users);
    }

    public List<Users> fetchAllUsers(){
        List<Users> allUsers = usersRepository.findAll();
        log.info("Fetched User list");
        return allUsers;
    }

    public Users updateUser(Long id, Users user) throws UserNotFoundException {
        Optional<Users> isExisting = usersRepository.findById(id);

        if (!isExisting.isPresent()) {
            throw new UserNotFoundException("Employee not found");
        }

        Users userDB = isExisting.get();

        if(Objects.nonNull(user.getUserName()) &&
                !"".equalsIgnoreCase(user.getUserName())) {
            userDB.setUserName(user.getUserName());
        }
        if(Objects.nonNull(user.getPassword()) &&
                !"".equalsIgnoreCase(user.getPassword())) {
            userDB.setPassword(user.getPassword());
        }
        log.info("User updated successfullly");
        return (Users) usersRepository.save(userDB);
    }

    public Users getUserById(Long id)throws UserNotFoundException {
        Optional<Users> users = usersRepository.findById(id);
        if (users.isPresent()) {
            log.info("Successfully Fetched User with given id");
            return users.get();
        }
        return null;
    }


    public String deleteUsersById(Long id) throws UserNotFoundException {
        if (usersRepository.findById(id).isPresent()) {
            usersRepository.deleteById(id);
            log.info("User deleted sucessfully");
            return "User deleted successfully";
        }
        return "No such user in the database";
    }


}