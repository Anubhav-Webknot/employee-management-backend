package com.employeemanagementbackend.employeemanagementbackend.service;

import com.employeemanagementbackend.employeemanagementbackend.model.employeeModel;
import com.employeemanagementbackend.employeemanagementbackend.model.timesheetModel;
import com.employeemanagementbackend.employeemanagementbackend.model.usersModel;
import com.employeemanagementbackend.employeemanagementbackend.repository.TimesheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TimesheetService {

    @Autowired
    TimesheetRepository timesheetRepository;

    public timesheetModel saveTimesheet(timesheetModel timesheet) {
        return timesheetRepository.save(timesheet);
    }

    public List<timesheetModel> fetchAllTimesheets() {
        return timesheetRepository.findAll();
    }

    public timesheetModel getTimesheetById(Long Id) {
        Optional<timesheetModel> timesheetRecord = timesheetRepository.findById(Id);
        if (timesheetRecord.isPresent()) {
            return timesheetRecord.get();
        }
        return null;
    }

    public timesheetModel updateTimesheetById(Long id, timesheetModel timesheet) {
        Optional<timesheetModel> timesheet1 = timesheetRepository.findById(id);

        if (timesheet1.isPresent()) {
            timesheetModel originalTimesheet = timesheet1.get();

            if (Objects.nonNull(timesheet.getProjectName()) && !"".equalsIgnoreCase(timesheet.getProjectName())) {
                originalTimesheet.setProjectName(timesheet.getProjectName());
            }

            if (Objects.nonNull(timesheet.getEmployeeName()) && !"".equalsIgnoreCase(timesheet.getEmployeeName())) {
                originalTimesheet.setEmployeeName(timesheet.getEmployeeName());
            }
            if (Objects.nonNull(timesheet.getDuration()) && timesheet.getDuration() != 0) {
                originalTimesheet.setDuration(timesheet.getDuration());
            }
            if (Objects.nonNull(timesheet.getDescription()) && !"".equalsIgnoreCase(timesheet.getDescription())) {
                originalTimesheet.setDescription(timesheet.getDescription());
            }
            return timesheetRepository.save(originalTimesheet);
        }
        return null;
    }

    public String deleteTimesheet(Long Id) {
        Optional<timesheetModel> timesheetRecord = timesheetRepository.findById(Id);
        if(!timesheetRecord.isPresent()){
            return ("Employee with id:"+Id+" not found !!");
        }
        timesheetRepository.deleteById(Id);
        return "User deleted successfully";
    }



}