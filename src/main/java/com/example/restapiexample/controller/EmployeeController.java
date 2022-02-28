package com.example.restapiexample.controller;

import com.example.restapiexample.dto.ApiResponse;
import com.example.restapiexample.dto.EmployeeDTO;
import com.example.restapiexample.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
@RequiredArgsConstructor
public class EmployeeController {

    final EmployeeService employeeService;


    @PostMapping
    public HttpEntity<?> add(@RequestBody EmployeeDTO dto) {
        ApiResponse response = employeeService.add(dto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public HttpEntity<?> getAll() {
        ApiResponse all = employeeService.getAll();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        ApiResponse response = employeeService.getOne(id);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> edit(@PathVariable Integer id, @RequestBody EmployeeDTO dto) {
        ApiResponse response = employeeService.edit(id, dto);
        return ResponseEntity.ok(response);
    }

}
