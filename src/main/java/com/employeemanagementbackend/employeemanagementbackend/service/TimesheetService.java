package com.employeemanagementbackend.employeemanagementbackend.service;

import com.employeemanagementbackend.employeemanagementbackend.exception.TimesheetNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.Timesheet;
import com.employeemanagementbackend.employeemanagementbackend.repository.TimesheetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class TimesheetService {

    @Autowired
    TimesheetRepository timesheetRepository;

    public Timesheet saveTimesheet(Timesheet timesheet) {
        log.info("creating new timesheet");
        return timesheetRepository.save(timesheet);
    }

    public List<Timesheet> fetchAllTimesheets() {
        log.info("Fetching all timesheets");
        return timesheetRepository.findAll();
    }

    public Timesheet getTimesheetById(Long Id)throws TimesheetNotFoundException {
        Optional<Timesheet> timesheetRecord = timesheetRepository.findById(Id);
        if (timesheetRecord.isPresent()) {
            return timesheetRecord.get();
        }
        return null;
    }

    public Timesheet updateTimesheetById(Long id, Timesheet timesheet)throws TimesheetNotFoundException {
        Optional<Timesheet> timesheet1 = timesheetRepository.findById(id);

        if (timesheet1.isPresent()) {
            Timesheet originalTimesheet = timesheet1.get();

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
            log.info("User updated sucessfully");
            return timesheetRepository.save(originalTimesheet);
        }
        return null;
    }

    public String deleteTimesheet(Long Id) throws TimesheetNotFoundException{
        Optional<Timesheet> timesheetRecord = timesheetRepository.findById(Id);
        if(!timesheetRecord.isPresent()){
            return ("Employee with id:"+Id+" not found !!");
        }
        timesheetRepository.deleteById(Id);
        log.info("User deleted sucessfully");
        return "User deleted successfully";
    }



}