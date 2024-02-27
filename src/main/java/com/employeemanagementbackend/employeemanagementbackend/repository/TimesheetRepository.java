package com.employeemanagementbackend.employeemanagementbackend.repository;

import com.employeemanagementbackend.employeemanagementbackend.model.timesheetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TimesheetRepository extends JpaRepository<timesheetModel,Long> {
}
