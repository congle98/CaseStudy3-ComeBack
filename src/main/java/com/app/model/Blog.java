package com.app.model;

import java.time.LocalDate;

public class Blog {
    private int id;
    private String description;
    private LocalDate date;
    private ClassOfAcademy ClassOfAcademy;

    public Blog(int id, String description, LocalDate date, ClassOfAcademy ClassOfAcademy) {
        this.id = id;
        this.description = description;
        this.date = date;
        this.ClassOfAcademy = ClassOfAcademy;
    }

    public Blog() {
    }

    public Blog(String description, LocalDate date, ClassOfAcademy ClassOfAcademy) {
        this.description = description;
        this.date = date;
        this.ClassOfAcademy = ClassOfAcademy;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ClassOfAcademy getClassOfAcademy() {
        return ClassOfAcademy;
    }

    public void setClassOfAcademy(com.app.model.ClassOfAcademy classOfAcademy) {
        ClassOfAcademy = classOfAcademy;
    }
}
