package com.dinesh.rest.webservies.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

@Entity(name = "user_details")
public class User {

    @Id
    @GeneratedValue
    private int id;
    @Size(min = 2, message = "Name Should be at Least 2 Characters")
    private String name;
    @Past
    private LocalDate dob;

    public User() {
    }

    public User(int id, String name, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}
