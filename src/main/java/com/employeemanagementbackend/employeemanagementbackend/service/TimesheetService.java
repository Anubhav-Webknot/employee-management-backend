package com.employeemanagementbackend.employeemanagementbackend.service;

import com.employeemanagementbackend.employeemanagementbackend.exception.TimesheetNotFoundException;
import com.employeemanagementbackend.employeemanagementbackend.model.Employee;
import com.employeemanagementbackend.employeemanagementbackend.model.Timesheet;
import com.employeemanagementbackend.employeemanagementbackend.repository.TimesheetRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@CacheConfig(cacheNames = {"Timesheet"})
public class TimesheetService {

    @Autowired
    TimesheetRepository timesheetRepository;

    @CacheEvict(value = "timesheet", allEntries = true)
    public Timesheet saveTimesheet(Timesheet timesheet) {
        log.info("creating new timesheet");
        return timesheetRepository.save(timesheet);
    }

    @Cacheable("Timesheet")
    public List<Timesheet> fetchAllTimesheets() {
        log.info("Fetching all timesheets");
        return timesheetRepository.findAll();
    }

    public List<Timesheet>FindTimesheetWithSorting(String field)
    {
        return timesheetRepository.findAll(Sort.by(Sort.Direction.ASC,field));
    }

    public Page<Timesheet> findTimesheetWithPagination(int offset, int pageSize)
    {
        return timesheetRepository.findAll(PageRequest.of(offset,pageSize));
    }

    @Cacheable(value = "timesheet" , key="#id")
    public Timesheet getTimesheetById(Long Id)throws TimesheetNotFoundException {
        Optional<Timesheet> timesheetRecord = timesheetRepository.findById(Id);
        if (timesheetRecord.isPresent()) {
            return timesheetRecord.get();
        }
        return null;
    }
    @CacheEvict(value = "timesheet",key="#id")
    // @CachePut(value = "blogs", key = "#id")
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

    @CacheEvict(value = "timesheet",allEntries = true)
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