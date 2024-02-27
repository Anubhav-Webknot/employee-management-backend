package com.employeemanagementbackend.employeemanagementbackend.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class usersModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long usersId;

    private String userName;

    private String password;
}
