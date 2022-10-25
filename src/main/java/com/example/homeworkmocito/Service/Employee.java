package com.example.homeworkmocito.Service;

import java.util.Objects;
import static org.apache.commons.lang3.StringUtils.capitalize;

public class Employee {
    private String name;
    private String surName;
    private int depId;
    private double salary;

    public Employee(String name, String surName, int depId, double salary) {
        this.name = capitalize(name.toLowerCase());
        this.surName = capitalize(surName.toLowerCase());
        this.depId = depId;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return depId == employee.depId && Double.compare(employee.salary, salary) == 0 && Objects.equals(name, employee.name) && Objects.equals(surName, employee.surName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surName);
    }

    @Override
    public String toString () {
        return "Имя - "+name+", Фамилия - "+surName+", отдел - "+depId+", зарплата - "+salary;
    }

}
