package com.example.restapiexample.service;

import com.example.restapiexample.dto.ApiResponse;
import com.example.restapiexample.dto.EmployeeDTO;
import com.example.restapiexample.entity.Department;
import com.example.restapiexample.entity.Employee;
import com.example.restapiexample.repository.DepartmentRepository;
import com.example.restapiexample.repository.EmployeeRepository;
import org.apache.catalina.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public ApiResponse add(EmployeeDTO dto) {
        Employee employee = modelMapper.map(dto, Employee.class);
        Employee save = employeeRepository.save(employee);
        return new ApiResponse("saved", true, save);

    }
}
