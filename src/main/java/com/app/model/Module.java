package com.app.model;

import java.time.LocalDate;
import java.util.List;

public class Module {
    private int id;
    private String name;
    private LocalDate dateStart;
    private LocalDate dateEnd;
    List<Student> studentList;


    public Module(int id, String name, LocalDate dateStart, LocalDate dateEnd, List<Student> studentList) {
        this.id = id;
        this.name = name;

        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.studentList = studentList;
    }

    public Module(String name,  LocalDate dateStart, LocalDate dateEnd, List<Student> studentList) {
        this.name = name;

        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.studentList = studentList;
    }

    public Module(int id, String name, LocalDate dateStart, LocalDate dateEnd) {
        this.id = id;
        this.name = name;
        this.dateEnd = dateEnd;
        this.dateStart = dateStart;

    }

    public Module(String name,  LocalDate dateStart, List<Student> studentList) {
        this.name = name;

        this.dateStart = dateStart;
        this.studentList = studentList;
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

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateStart=" + dateStart +
                ", dateEnd=" + dateEnd +
                ", studentList=" + studentList +
                '}';
    }
}
