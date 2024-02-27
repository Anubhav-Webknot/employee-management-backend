package com.employeemanagementbackend.employeemanagementbackend.repository;



import com.employeemanagementbackend.employeemanagementbackend.model.employeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<employeeModel,Long> {

}
