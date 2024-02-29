package com.employeemanagementbackend.employeemanagementbackend.service;

import com.employeemanagementbackend.employeemanagementbackend.exception.EmployeeNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.employeeModel;
import com.employeemanagementbackend.employeemanagementbackend.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public employeeModel saveEmployee(employeeModel employee){
        log.info("creating new timesheet");
        return employeeRepository.save(employee);
    }

    public List<employeeModel> fetchAllEmployees() {
        List<employeeModel> allEmployees = employeeRepository.findAll();
        log.info("Fetching all employees");
        return allEmployees;
    }

    public employeeModel getEmployeeById(Long id)throws EmployeeNotFoundException {
        Optional<employeeModel> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            log.info("Fetching employee with id ",+id);
            return employee.get();
        }
        return null;
    }

    public employeeModel updateEmployeeById(Long id, employeeModel employee) throws EmployeeNotFoundException{
        Optional<employeeModel> employee1 = employeeRepository.findById(id);

        if (employee1.isPresent()) {
            employeeModel originalEmployee = employee1.get();

            if (Objects.nonNull(employee.getEmployeeName()) && !"".equalsIgnoreCase(employee.getEmployeeName())) {
                originalEmployee.setEmployeeName(employee.getEmployeeName());
            }
            if (Objects.nonNull(employee.getEmployeeRole()) && !"".equalsIgnoreCase(employee.getEmployeeRole())) {
                originalEmployee.setEmployeeRole(employee.getEmployeeRole());
            }
            if (Objects.nonNull(employee.getEmployeePhone()) && !"".equalsIgnoreCase(employee.getEmployeePhone())) {
                originalEmployee.setEmployeePhone(employee.getEmployeePhone());
            }
            if (Objects.nonNull(employee.getEmployeeEmail()) && !"".equalsIgnoreCase(employee.getEmployeeEmail())) {
                originalEmployee.setEmployeeEmail(employee.getEmployeeEmail());
            }
            log.info("Updating employee with id ",+id);
            return employeeRepository.save(originalEmployee);
        }
        return null;
    }

    public String deleteDepartmentById(Long id) throws EmployeeNotFoundException {
        if (employeeRepository.findById(id).isPresent()) {
            employeeRepository.deleteById(id);
            log.info("Deleting employee with id ",+id);
            return "Employee deleted successfully";
        }
        return "No such employee in the database";
    }

}
