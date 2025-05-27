package com.bhushan.RestAPI.Spring.Boot.Web.MVC.services;

import com.bhushan.RestAPI.Spring.Boot.Web.MVC.dtos.DepartmentDto;
import com.bhushan.RestAPI.Spring.Boot.Web.MVC.entities.Department;
import com.bhushan.RestAPI.Spring.Boot.Web.MVC.exceptions.ResourceNotFoundException;
import com.bhushan.RestAPI.Spring.Boot.Web.MVC.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper mapper;

    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper mapper) {
        this.departmentRepository = departmentRepository;
        this.mapper = mapper;
    }

    public Optional<DepartmentDto> getDepartmentById(Long deptId) {
        isExistsByDepartmentId(deptId);
        Optional<Department> department = departmentRepository.findById(deptId);
        return department.map(department1 -> mapper.map(department1,DepartmentDto.class));
    }


    public DepartmentDto createDepartment(DepartmentDto inputDepartment) {

        Department saveDept = mapper.map(inputDepartment,Department.class);
        Department dept = departmentRepository.save(saveDept);
        return mapper.map(dept,DepartmentDto.class);
    }

    public List<DepartmentDto> getAllDepartments() {
        List<Department> depts = departmentRepository.findAll();
        return depts
                .stream()
                .map(dp -> mapper.map(dp,DepartmentDto.class))
                .collect(Collectors.toList());
    }

    public DepartmentDto updateDepartmentById(Long deptId, DepartmentDto dept) {
        isExistsByDepartmentId(deptId);
        Department department = mapper.map(dept, Department.class);
        department.setId(deptId);
        Department saveDept = departmentRepository.save(department);
        return mapper.map(saveDept,DepartmentDto.class);
    }

    public Boolean deleteDepartmentById(Long deptId) {
        isExistsByDepartmentId(deptId);
        departmentRepository.deleteById(deptId);
        return true;
    }

    public DepartmentDto updatePartialDepartmentById(Long deptId, Map<String,Object> updates) {
        isExistsByDepartmentId(deptId);
        Department department = departmentRepository.findById(deptId).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdated = ReflectionUtils.findRequiredField(Department.class, field);
            fieldToBeUpdated.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdated, department, value);
        });
        return mapper.map(departmentRepository.save(department),DepartmentDto.class);
    }

    public void isExistsByDepartmentId(Long deptId){
        boolean exists = departmentRepository.existsById(deptId);
        if(!exists) throw new ResourceNotFoundException("Department Not found with Id : "+deptId);
    }
}


