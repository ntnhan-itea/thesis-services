package com.edu.ctu.thesis.employee;

import com.edu.ctu.thesis.exceptions.EmployeeException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/employee")
@CrossOrigin
@Log4j2
public class EmployeeResource {

    @Autowired
    EmployeeService employeeService;

    @Value("${user.my.name}")
    private String userName;

    @GetMapping
    public List<Employee> getAll() {
        log.info("Calling get all employee... {}", 12);
        log.info("============ " + userName);
//        log.warn("Calling get all employee...");
//        log.error("Calling get all employee...");

        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        try {
            return employeeService.getEmployeeById(id);
        } catch (EmployeeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/search")
    public List<Employee> getEmployeeByBirthday(@RequestParam("birthday")
                                                @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                LocalDate birthday) {
        try {
            return employeeService.findByBirthday(birthday);
        } catch (EmployeeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        try {
            return employeeService.createEmployee(employee);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }


}
