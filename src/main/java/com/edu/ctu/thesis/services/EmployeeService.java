package com.edu.ctu.thesis.services;

import com.edu.ctu.thesis.entities.Employee;
import com.edu.ctu.thesis.exceptions.EmployeeException;
import com.edu.ctu.thesis.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) throws EmployeeException {
        Optional<Employee> employee = employeeRepository.findById(id);

        if(!employee.isPresent())
            throw new EmployeeException("Employee Not Found in EmployeeRepository");

        return employee.get();
    }

    public Employee createEmployee(Employee employee) throws EmployeeException {
        Employee existEmployee = employeeRepository.findByFirstName(employee.getFirstName());
        if(existEmployee != null) {
            throw new EmployeeException("Employee already exist!");
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> findByBirthday(LocalDate birthday) throws EmployeeException {
        List<Employee> existEmployees = employeeRepository.findByBirthday(birthday);
        if(existEmployees.isEmpty()) {
            throw new EmployeeException("Employee not found!");
        }
        return existEmployees;
    }

}
