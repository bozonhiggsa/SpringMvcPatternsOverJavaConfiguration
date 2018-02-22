package com.application.springMvc.model;

/**
 * Model of an employee
 * @author Ihor Savchenko
 * @version 1.0
 */
public class Employee {

    private long id;
    private String name;
    private String contactNumber;

    public Employee(long id, String name, String contactNumber) {
        this.id = id;
        this.name = name;
        this.contactNumber = contactNumber;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

}
