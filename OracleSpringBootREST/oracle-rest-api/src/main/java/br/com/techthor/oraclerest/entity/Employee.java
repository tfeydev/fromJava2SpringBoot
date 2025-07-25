package br.com.techthor.oraclerest.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phone;

    @Column(name = "HIRE_DATE")
    private java.sql.Date hireDate;

    @Column(name = "JOB_ID")
    private String jobId;

    // getters/setters

}
