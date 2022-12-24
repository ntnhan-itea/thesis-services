package com.edu.ctu.thesis.employee;

import com.edu.ctu.thesis.exceptions.EmployeeException;
import com.edu.ctu.thesis.file.FileService;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    @Autowired
    FileService fileService;

    @Value("${user.my.name}")
    private String userName;

    @GetMapping
    public List<Employee> getAll() {
        log.info("Calling get all employee... {}", 12);
        log.info("============ " + userName);
        // log.warn("Calling get all employee...");
        // log.error("Calling get all employee...");

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
    public List<Employee> getEmployeeByBirthday(
            @RequestParam("birthday") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate birthday) {
        try {
            return employeeService.findByBirthday(birthday);
        } catch (EmployeeException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        try {
            return employeeService.createEmployee(employee);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

    @PostMapping(path = "create", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE,
            MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> createEmployee2(@RequestPart("employee") Employee employee,
            @RequestPart("file") MultipartFile file) {
        try {
            this.fileService.uploadFie(file);
            return ResponseEntity.ok(employeeService.createEmployee(employee));
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
