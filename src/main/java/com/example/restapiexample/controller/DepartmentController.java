package com.example.restapiexample.controller;

import com.example.restapiexample.dto.ApiResponse;
import com.example.restapiexample.dto.DepartmentDTO;
import com.example.restapiexample.entity.Department;
import com.example.restapiexample.service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public HttpEntity<?> add(@RequestBody DepartmentDTO dto) {
        // dto ->  department
        Department department = modelMapper.map(dto, Department.class);
        ApiResponse response = departmentService.add(department);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        List<DepartmentDTO> collect = departmentService.getAll().stream().map(
                        department -> modelMapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(collect);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getById(@PathVariable Integer id) {
        ApiResponse response = departmentService.getOne(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody DepartmentDTO dto) {
        ApiResponse response = departmentService.edit(id, dto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        ApiResponse response = departmentService.delete(id);
        return ResponseEntity.ok().body(response);

    }
}
