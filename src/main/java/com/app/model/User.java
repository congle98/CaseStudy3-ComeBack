package com.app.model;

import java.time.LocalDate;

public abstract class User {
    private int id;
    private String name;
    private String  email;
    private String password;
    private String url;
    private Address address;
    private LocalDate dob ;
    private Status status;

    public User() {
    }

    public User(int id, String name, String email, String password, String url, Address address, LocalDate dob,Status status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.url = url;
        this.address = address;
        this.dob = dob;
        this.status = status;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public User(String name, String email, String password, String url, Address address, LocalDate dob, Status status) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.url = url;
        this.address = address;
        this.dob = dob;
        this.status = status;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                ", address=" + address +
                ", dob=" + dob +
                ", status=" + status +
                '}';
    }
}
