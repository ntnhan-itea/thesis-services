package com.edu.ctu.thesis.employee;

import com.edu.ctu.thesis.employee.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByFirstName(String firstName);

    @Query("from Employee e where e.birthday = :birthday")
    List<Employee> findByBirthday(@Param("birthday") LocalDate birthday);
}
