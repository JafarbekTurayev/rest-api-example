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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class EmployeeService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public ApiResponse add(EmployeeDTO dto) {
//        Employee employee = modelMapper.map(dto, Employee.class);
//        Department department = modelMapper.map(dto, Department.class);
//        employee.setDepartment(department);
        Employee employee = new Employee();
        employee.setName(dto.getName());
        employee.setPhone(dto.getPhone());
        employee.setSerial(dto.getSerial());
        employee.setNumber(dto.getNumber());
        employee.setSalary(dto.getSalary());
//        Department one = departmentRepository.getOne(dto.getDepartmentId());
        Optional<Department> departmentOptional = departmentRepository.findById(dto.getDepartmentId());
        employee.setDepartment(departmentOptional.get());

        Employee save = employeeRepository.save(employee);
        return new ApiResponse("saved", true, save);

    }

    public ApiResponse getAll() {
        List<Employee> all = employeeRepository.findAll();
        return new ApiResponse("Mana", true, all);
    }

    public ApiResponse getOne(Integer id) {
        return new ApiResponse("Mana", true, employeeRepository.findById(id).get());
    }

    public ApiResponse edit(Integer id, EmployeeDTO dto) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Xatolik", false);
        Employee edited = byId.get();
        edited.setName(dto.getName());
        edited.setSalary(dto.getSalary());
        edited.setSerial(dto.getSerial());
        edited.setNumber(dto.getNumber());
        Optional<Department> byId1 = departmentRepository.findById(dto.getDepartmentId());
        edited.setDepartment(byId1.get());
        Employee save = employeeRepository.save(edited);
        return new ApiResponse("Mana", true, save);

    }

    public ApiResponse delete(Integer id) {
        if (!employeeRepository.existsById(id)) return new ApiResponse("Xatolik", false);
        employeeRepository.deleteById(id);
        return new ApiResponse("Delete", true);
    }
}
