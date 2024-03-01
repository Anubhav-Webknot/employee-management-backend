package com.employeemanagementbackend.employeemanagementbackend.repository;

import com.employeemanagementbackend.employeemanagementbackend.model.Timesheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  TimesheetRepository extends JpaRepository<Timesheet,Long> {
}
