package com.bhushan.RestAPI.Spring.Boot.Web.MVC.repositories;

import com.bhushan.RestAPI.Spring.Boot.Web.MVC.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

}
