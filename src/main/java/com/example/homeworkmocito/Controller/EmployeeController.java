package com.example.homeworkmocito.Controller;

import com.example.homeworkmocito.Service.Employee;
import com.example.homeworkmocito.Service.EmployeeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/add")
    public Object addEmployee(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname,
            @RequestParam(value = "depId") int depId,
            @RequestParam(value = "salary") double salary) {
        Employee employee = null;
        try {
            employee = employeeService.addEmployee(name, surname, depId, salary);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/remove")
    public Object removeEmployee(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname) {
        Employee employee = null;
        try {
            employee = employeeService.removeEmployee(name, surname);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }

    @GetMapping(path = "/find")
    public Object findEmployee(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "surname") String surname) {
        Employee employee = null;
        try {
            employee = employeeService.findEmployee(name, surname);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }
}
