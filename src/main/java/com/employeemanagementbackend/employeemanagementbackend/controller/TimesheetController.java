package com.employeemanagementbackend.employeemanagementbackend.controller;

import com.employeemanagementbackend.employeemanagementbackend.exception.TimesheetNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.Timesheet;
import com.employeemanagementbackend.employeemanagementbackend.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TimesheetController {


    @Autowired
    private TimesheetService timesheetService;

    @GetMapping("/timesheet")
    public List<Timesheet> fetchTimesheetList() {
        return timesheetService.fetchAllTimesheets();
    }

    @PostMapping("/timesheet")
    public Timesheet saveTimesheet(@RequestBody Timesheet timesheet) {
        return timesheetService.saveTimesheet(timesheet);
    }

    @GetMapping("/timesheet/{id}")
    public Timesheet fetchTimesheetById(@PathVariable Long id) throws TimesheetNotFoundException {
        return timesheetService.getTimesheetById(id);
    }

    @PutMapping("/timesheet/{id}")
    public Timesheet updateTimesheet(@PathVariable("id") Long Id, @RequestBody Timesheet timesheet) throws TimesheetNotFoundException {
        return timesheetService.updateTimesheetById(Id,timesheet);
    }

    @DeleteMapping("/timesheet/{id}")
    public String deleteTimesheet(@PathVariable Long id)throws TimesheetNotFoundException{
        return timesheetService.deleteTimesheet(id);
    }

}