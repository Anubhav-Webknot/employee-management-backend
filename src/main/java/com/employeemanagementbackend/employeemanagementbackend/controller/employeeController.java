package com.employeemanagementbackend.employeemanagementbackend.controller;


import com.employeemanagementbackend.employeemanagementbackend.exception.EmployeeNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.employeeModel;
import com.employeemanagementbackend.employeemanagementbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class employeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public employeeModel saveEmployee(@RequestBody employeeModel employee)
    {
        return employeeService.saveEmployee(employee);
    }


    @GetMapping("/employee")
    public List<employeeModel> getAllEmployees()throws EmployeeNotFoundException{

        return employeeService.fetchAllEmployees();
    }

    @GetMapping("/employee/{id}")
    public employeeModel getEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {

        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employee/{id}")
    public employeeModel updateEmployee(@PathVariable("id") Long id, @RequestBody employeeModel employee)throws EmployeeNotFoundException {
        return employeeService.updateEmployeeById(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long id)throws EmployeeNotFoundException {

        return employeeService.deleteDepartmentById(id);
    }
}


