package com.example.restapiexample.repository;


import com.example.restapiexample.entity.Address;
import com.example.restapiexample.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
