package com.clopezrubio.springboot.cruddemo.service;

import com.clopezrubio.springboot.cruddemo.dao.EmployeeRepository;
import com.clopezrubio.springboot.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeerepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeerepository) {
        this.employeerepository = employeerepository;
    }

    @Override
    public List<Employee> findAll() {
        return employeerepository.findAll();
    }


    @Override
    public Employee findById(int id) {
        Optional<Employee> result = employeerepository.findById(id);

        Employee theEmployee = null;

        if (result.isPresent()) {
            theEmployee =  result.get();
        }else{
            // we didn't find the employee
            throw new RuntimeException("Employee with id " + id + " not found");
        }

        return theEmployee;
    }

    @Override
    public Employee save(Employee employee) {
        return employeerepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeerepository.deleteById(id);
    }

}
