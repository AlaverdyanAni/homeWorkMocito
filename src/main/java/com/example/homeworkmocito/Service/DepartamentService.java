package com.example.homeworkmocito.Service;

import java.util.List;

public interface DepartamentService {

    Employee getEmployeeWithMaxSalaryFromDepartament(int depId);

    Employee getEmployeeWithMinSalaryFromDepartament(int depId);

    List<Employee> getEmployeesFromDepartment(int depId);

    List<Employee> getAllEmployeesByDepartaments();
}
