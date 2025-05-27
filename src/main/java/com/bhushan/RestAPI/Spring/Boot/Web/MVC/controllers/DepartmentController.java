package com.bhushan.RestAPI.Spring.Boot.Web.MVC.controllers;

import com.bhushan.RestAPI.Spring.Boot.Web.MVC.dtos.DepartmentDto;
import com.bhushan.RestAPI.Spring.Boot.Web.MVC.exceptions.ResourceNotFoundException;
import com.bhushan.RestAPI.Spring.Boot.Web.MVC.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartment(){
        return  ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping(path="/{deptId}")
    public ResponseEntity<DepartmentDto> getDepartment(@PathVariable Long deptId){
        Optional<DepartmentDto> departmentDto =  departmentService.getDepartmentById(deptId);
        return departmentDto
                .map(departmentDto1 -> ResponseEntity.ok(departmentDto1))
                .orElseThrow(()-> new ResourceNotFoundException("Department Not Found With Id : "+deptId));
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){
        DepartmentDto departmentDto1 =   departmentService.createDepartment(departmentDto);
        return new ResponseEntity<>(departmentDto1, HttpStatus.CREATED);
    }

    @PutMapping(path = "{deptId}")
    public ResponseEntity<DepartmentDto> updateDepartmentById(@PathVariable Long deptId, @RequestBody DepartmentDto dept){
        return ResponseEntity.ok(departmentService.updateDepartmentById(deptId,dept));
    }

    @DeleteMapping(path = "{deptId}")
    public ResponseEntity<Boolean> deleteDepartmentById(@PathVariable Long deptId){
        boolean gotDeleted =  departmentService.deleteDepartmentById(deptId);
        if(gotDeleted)return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }


    @PatchMapping(path = "{deptId}")
    public ResponseEntity<DepartmentDto> updatePartialDepartmentById(@PathVariable Long deptId, @RequestBody Map<String,Object> updates){
        DepartmentDto departmentDto =  departmentService.updatePartialDepartmentById(deptId, updates);
        if(departmentDto==null)return ResponseEntity.notFound().build();
        return ResponseEntity.ok(departmentDto);
    }
}
