package com.edu.ctu.thesis.repositories;

import com.edu.ctu.thesis.entities.Employee;
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
