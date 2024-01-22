package com.wulp.assignment.model;


import java.time.Instant;


public class Employee {

    private String name;
    private int age;
    private String departmentName;
    private Instant startDate;
    private Instant endDate;

    public Employee(String name, int age, String departmentName, Instant startDate, Instant endDate) {
        this.name = name;
        this.age = age;
        this.departmentName = departmentName;
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }
}
