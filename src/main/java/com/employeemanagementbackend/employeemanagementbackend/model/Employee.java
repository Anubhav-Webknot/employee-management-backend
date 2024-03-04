package com.employeemanagementbackend.employeemanagementbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Employee implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long employeeId;

    private String employeeName;

    private String employeeRole;

    private String employeePhone;

    private String employeeEmail;

    private String employeeDepartment;

    private Date joiningDate;

    private Date leavingDate;

    private Double salary;

}
