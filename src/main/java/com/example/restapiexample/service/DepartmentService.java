package com.example.restapiexample.service;

import com.example.restapiexample.dto.ApiResponse;
import com.example.restapiexample.dto.DepartmentDTO;
import com.example.restapiexample.entity.Department;
import com.example.restapiexample.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    public ApiResponse add(Department department) {
        Department save = departmentRepository.save(department);
        return new ApiResponse("Added!", true, save);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Department> byId = departmentRepository.findById(id);
        Department department = byId.get();
        DepartmentDTO map = modelMapper.map(department, DepartmentDTO.class);

        return new ApiResponse("Mana", true, map);
    }

    public ApiResponse edit(Integer id, DepartmentDTO dto) {
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Xatolik", false);

        Department edited = byId.get();
        Department department = modelMapper.map(dto, edited.getClass());
        department.setId(id);
        departmentRepository.save(department);
        return new ApiResponse("edited", true);

    }

    public ApiResponse delete(Integer id) {
        if (!departmentRepository.existsById(id)) return new ApiResponse("Xatolik", false);

        departmentRepository.deleteById(id);
        return new ApiResponse("Mana", true);
    }
}
