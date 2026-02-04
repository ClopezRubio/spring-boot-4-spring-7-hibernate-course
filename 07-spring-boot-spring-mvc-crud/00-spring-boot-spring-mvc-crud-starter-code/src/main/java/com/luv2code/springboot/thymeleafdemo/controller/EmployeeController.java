package com.luv2code.springboot.thymeleafdemo.controller;


import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
            employeeService = theEmployeeService;
    }

    // add mapping for "/list"

    @GetMapping("/list")
    public String list(Model theModel) {

        // get the employees from the db

        List<Employee> theEmployees = employeeService.findAll();

        // add to the srping model

        theModel.addAttribute("employees", theEmployees);

        return "employees/list-employees";
    }

    // add mapping for "/showFormForAdd"
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {

        // create model attribute to bind form data
        Employee theEmployee = new Employee();

        theModel.addAttribute("employee", theEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

        // save the employee

        employeeService.save(theEmployee);

        // use a redirect to prevent duplicate submissions

        return "redirect:/employees/list";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

        // find the employee using the ID

        Employee theEmployee = employeeService.findById(theId);

        // set employee in the model to prepopulate the form

        theModel.addAttribute("employee", theEmployee);

        // send over to our form

        return "employees/employee-form";

    }

    @GetMapping("/delete")
    public String delete(@RequestParam("employeeId") int theId) {

        // delete the employee

        employeeService.deleteById(theId);

        // redirect to the /employees/list

        return "redirect:/employees/list";
    }

}
