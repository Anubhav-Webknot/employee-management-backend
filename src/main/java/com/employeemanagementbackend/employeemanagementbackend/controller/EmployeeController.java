package com.employeemanagementbackend.employeemanagementbackend.controller;


import com.employeemanagementbackend.employeemanagementbackend.exception.EmployeeNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.Employee;
import com.employeemanagementbackend.employeemanagementbackend.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee saveEmployee(@RequestBody Employee employee)
    {
        return employeeService.saveEmployee(employee);
    }


    @GetMapping("/employee")
    public List<Employee> getAllEmployees()throws EmployeeNotFoundException{

        return employeeService.fetchAllEmployees();
    }

    @GetMapping("/sort-{field}")
    public List<Employee> getAllEmployeesWithSort(@PathVariable String field)throws EmployeeNotFoundException{

        return employeeService.FindEmployeeWithSorting(field);
    }

    @GetMapping("/employee-pagination/{offset}/{pageSize}")
    public Page<Employee> getAllEmployeesWithSort(@PathVariable int offset,@PathVariable int pageSize)throws EmployeeNotFoundException{

        return employeeService.findEmployeeWithPagination(offset,pageSize);
    }

    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) throws EmployeeNotFoundException {

        return employeeService.getEmployeeById(id);
    }

    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee)throws EmployeeNotFoundException {
        return employeeService.updateEmployeeById(id, employee);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long id)throws EmployeeNotFoundException {

        return employeeService.deleteDepartmentById(id);
    }
}


