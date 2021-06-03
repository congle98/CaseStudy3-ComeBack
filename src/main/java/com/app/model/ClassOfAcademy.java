package com.app.model;

public class ClassOfAcademy {
    private int id;
    private String name;
    private Teacher teacher;
    private Course course;

    public ClassOfAcademy(int id, String name, Teacher teacher, Course course) {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.course = course;
    }
    public ClassOfAcademy(int id, String name, Course course){
        this.id = id;
        this.name = name;
        this.course = course;
    }
    public ClassOfAcademy(String name, Teacher teacher, Course course) {
        this.name = name;
        this.teacher = teacher;
        this.course = course;
    }

    public ClassOfAcademy() {
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

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
