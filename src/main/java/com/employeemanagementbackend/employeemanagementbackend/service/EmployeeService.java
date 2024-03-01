package com.employeemanagementbackend.employeemanagementbackend.service;

import com.employeemanagementbackend.employeemanagementbackend.exception.EmployeeNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.Employee;
import com.employeemanagementbackend.employeemanagementbackend.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    public Employee saveEmployee(Employee employee){
        log.info("creating new timesheet");
        return employeeRepository.save(employee);
    }

    public List<Employee> fetchAllEmployees() {
        List<Employee> allEmployees = employeeRepository.findAll();
        log.info("Fetching all employees");
        return allEmployees;
    }

    public List<Employee>FindEmployeeWithSorting(String field)
    {
        return employeeRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

//    public Page<Employee>FindEmployeeWithPagination(int offset,int pageSize)
//    {
//       return employeeRepository.findAll(PageRequest.of(offset,pageSize));
//    }

    public Employee getEmployeeById(Long id)throws EmployeeNotFoundException {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            log.info("Fetching employee with id ",+id);
            return employee.get();
        }
        return null;
    }

    public Employee updateEmployeeById(Long id, Employee employee) throws EmployeeNotFoundException{
        Optional<Employee> employee1 = employeeRepository.findById(id);

        if (employee1.isPresent()) {
            Employee originalEmployee = employee1.get();

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
