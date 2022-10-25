package com.example.homeworkmocito.Controller;

import com.example.homeworkmocito.Service.DepartamentServiceImpl;
import com.example.homeworkmocito.Service.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/departament")
public class Departamentcontroller {
    private final DepartamentServiceImpl departamentService;
    public Departamentcontroller(DepartamentServiceImpl departamentService){
        this.departamentService=departamentService;
    }

   @GetMapping(path = "/max-salary")
   public Object getMaxSalaryEmployee(
        @RequestParam(value = "depId") int depId) {
    Employee employee = null;
    try {
        employee = departamentService.getEmployeeWithMaxSalaryFromDepartament(depId);
    } catch (Throwable e) {
        return e.getMessage();
    }
    return employee;
}
    @GetMapping(path = "/min-salary")
    public Object getMinSalaryEmployee(
            @RequestParam(value = "depId") int depId) {
        Employee employee = null;
        try {
            employee = departamentService.getEmployeeWithMinSalaryFromDepartament(depId);

        } catch (Throwable e) {
            return e.getMessage();
        }
        return employee;
    }
    @GetMapping(path = "/all-dep")
    public Object printEmployeesForDepartment(
            @RequestParam(value = "depId") int depId) {
        List<Employee> employees = null;
        try {
            employees = departamentService.getEmployeesFromDepartment(depId);
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }

    @GetMapping(path = "/all")
    public Object EmployeesByDepartments() {
        List<Employee> employees = null;
        try {
            employees = departamentService.getAllEmployeesByDepartaments();
        } catch (Throwable e) {
            return e.getMessage();
        }
        return employees;
    }
}
