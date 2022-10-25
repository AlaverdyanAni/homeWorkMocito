package com.example.homeworkmocito.Service;

import java.util.List;

public interface EmployeeService {
    Employee addEmployee(String name, String surName, int depId, double salary);
    Employee removeEmployee(String name, String surName);
    Employee findEmployee(String name, String surName);
    List<Employee> getAllEmployees();

}
