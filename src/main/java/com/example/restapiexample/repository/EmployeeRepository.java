package com.example.restapiexample.repository;


import com.example.restapiexample.entity.Address;
import com.example.restapiexample.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
