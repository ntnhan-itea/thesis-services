package com.edu.ctu.thesis.employee;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByFirstName(String firstName);

    Employee findByAccount(String account);

    @Query("from Employee e where e.birthday = :birthday")
    List<Employee> findByBirthday(@Param("birthday") LocalDate birthday);
}
