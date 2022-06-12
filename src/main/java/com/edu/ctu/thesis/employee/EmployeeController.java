package com.edu.ctu.thesis.employee;

import com.edu.ctu.thesis.exceptions.EmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/api/employee")
@CrossOrigin
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id) {
        try {
            return employeeService.getEmployeeById(id);
        }catch (EmployeeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @GetMapping("/search")
    public List<Employee> getEmployeeByBirthday(@RequestParam("birthday")
                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                LocalDate birthday) {
        try {
            return employeeService.findByBirthday(birthday);
        }catch (EmployeeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        try {
            return employeeService.createEmployee(employee);
        }catch (EmployeeException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
