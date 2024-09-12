package com.dinesh.rest.webservies.person;

public class PersonV1 {

    private String Name;

    public PersonV1(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    @Override
    public String toString() {
        return "PersonV1{" +
                "Name='" + Name + '\'' +
                '}';
    }
}
