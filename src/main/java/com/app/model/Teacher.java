package com.app.model;

import java.time.LocalDate;

public class Teacher extends User{

    public Teacher() {
    }

    public Teacher(int id, String name, String email, String password, String url, Address address, LocalDate dob, Status status) {
        super(id, name, email, password, url, address, dob, status);
    }

    public Teacher(String email, String password) {
        super(email, password);
    }

    public Teacher(String name, String email, String password) {
        super(name, email, password);
    }

    public Teacher(String name, String email, String password, String url, Address address, LocalDate dob, Status status) {
        super(name, email, password, url, address, dob, status);
    }
}
