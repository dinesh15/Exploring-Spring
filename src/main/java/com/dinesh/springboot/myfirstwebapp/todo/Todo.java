package com.dinesh.springboot.myfirstwebapp.todo;

import jakarta.validation.constraints.Size;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

public class Todo {


    private String userName;
    private int id;


    @Size(min = 10, message = "size should be at least 10 characters")
    private String description;
//    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)//is this how you can specify the format
    private LocalDate targetDate;
    private boolean done;

    public Todo() {
    }

    public Todo( int id, String userName,  String description, LocalDate targetDate, boolean done) {
        this.userName = userName;
        this.id = id;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(LocalDate targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "userName='" + userName + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", done=" + done +
                '}';
    }
}
