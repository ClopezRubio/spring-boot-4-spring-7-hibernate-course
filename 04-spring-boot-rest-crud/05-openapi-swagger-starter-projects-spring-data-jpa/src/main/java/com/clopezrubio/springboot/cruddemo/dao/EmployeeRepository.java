package com.clopezrubio.springboot.cruddemo.dao;

import com.clopezrubio.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // no need to write any code, we get all the CRUD methods for free

}
