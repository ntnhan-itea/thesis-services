package com.edu.ctu.thesis.employee;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.edu.ctu.thesis.exceptions.EmployeeException;
import com.edu.ctu.thesis.util.ThesisUtils;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long id) throws EmployeeException {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (!employee.isPresent()) {
            throw new EmployeeException("Employee Not Found in EmployeeRepository");
        }

        return employee.get();
    }

    public Employee createEmployee(Employee employee) throws EmployeeException {
        String account = employee.getAccount();
        Employee existEmployee = employeeRepository.findByAccount(account);
        if (existEmployee != null) {
            throw new EmployeeException("Employee account [" + account + "] already exist!");
        }

        String passwordBase64 = ThesisUtils.encodeBase64(employee.getPassword());
        employee.setPassword(passwordBase64);
        return employeeRepository.save(employee);
    }

    public List<Employee> findByBirthday(LocalDate birthday) throws EmployeeException {
        List<Employee> existEmployees = employeeRepository.findByBirthday(birthday);
        if (existEmployees.isEmpty()) {
            throw new EmployeeException("Employee not found!");
        }
        return existEmployees;
    }

}
