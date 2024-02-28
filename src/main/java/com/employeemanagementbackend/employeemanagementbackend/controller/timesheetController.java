package com.employeemanagementbackend.employeemanagementbackend.controller;

import com.employeemanagementbackend.employeemanagementbackend.exception.TimesheetNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.timesheetModel;
import com.employeemanagementbackend.employeemanagementbackend.service.TimesheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class timesheetController {


    @Autowired
    private TimesheetService timesheetService;

    @GetMapping("/timesheet")
    public List<timesheetModel> fetchTimesheetList() {
        return timesheetService.fetchAllTimesheets();
    }

    @PostMapping("/timesheet")
    public timesheetModel saveTimesheet(@RequestBody timesheetModel timesheet) {
        return timesheetService.saveTimesheet(timesheet);
    }

    @GetMapping("/timesheet/{id}")
    public timesheetModel fetchTimesheetById(@PathVariable Long id) throws TimesheetNotFoundException {
        return timesheetService.getTimesheetById(id);
    }

    @PutMapping("/timesheet/{id}")
    public timesheetModel updateTimesheet(@PathVariable("id") Long Id,@RequestBody timesheetModel timesheet) throws TimesheetNotFoundException {
        return timesheetService.updateTimesheetById(Id,timesheet);
    }

    @DeleteMapping("/timesheet/{id}")
    public String deleteTimesheet(@PathVariable Long id)throws TimesheetNotFoundException{
        return timesheetService.deleteTimesheet(id);
    }

}