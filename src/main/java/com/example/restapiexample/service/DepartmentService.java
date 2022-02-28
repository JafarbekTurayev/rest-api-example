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

    public ApiResponse add(DepartmentDTO dto) {
        Department department = new Department();
        department.setDirector(dto.getDirector());
        department.setName(dto.getName());
        Department save = departmentRepository.save(department);
        return new ApiResponse("Added!", true, save);
    }

    public ApiResponse getOne(Integer id) {
        Optional<Department> byId = departmentRepository.findById(id);
        Department department = byId.get();
//        DepartmentDTO map = modelMapper.map(department, DepartmentDTO.class);
        return new ApiResponse("Mana", true, department);
    }

    public ApiResponse edit(Integer id, DepartmentDTO dto) {
        Optional<Department> byId = departmentRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Xatolik", false);

        Department edited = byId.get();
        edited.setDirector(dto.getDirector());
        edited.setName(dto.getName());
//        Department department = modelMapper.map(dto, edited.getClass());
        departmentRepository.save(edited);
        return new ApiResponse("edited", true);
    }

    public ApiResponse delete(Integer id) {
        if (!departmentRepository.existsById(id)) return new ApiResponse("Xatolik", false);
        departmentRepository.deleteById(id);
        return new ApiResponse("Mana", true);
    }
}
