package com.app.model;

import java.time.LocalDate;
import java.util.List;

public class Student extends User{
    private List<Module> moduleList;
    private ClassOfAcademy classOfAcademy;

    public Student(List<Module> moduleList, ClassOfAcademy classOfAcademy) {
        this.moduleList = moduleList;
        this.classOfAcademy = classOfAcademy;
    }

    public Student(int id, String name, String email, String password, String url, Address address, LocalDate dob, Status status, ClassOfAcademy classOfAcademy) {
        super(id, name, email, password, url, address, dob, status);

        this.classOfAcademy = classOfAcademy;
    }
    public Student(String name, String email, String password, String url, Address address, LocalDate dob, Status status,  ClassOfAcademy classOfAcademy) {
        super( name, email, password, url, address, dob, status);
        this.classOfAcademy = classOfAcademy;
    }
    public Student(int id, String name, String email, String password, String url, Address address, LocalDate dob, Status status) {
        super(id, name, email, password, url, address, dob, status);


    }

    public Student(String email, String password, List<Module> moduleList, ClassOfAcademy classOfAcademy) {
        super(email, password);
        this.moduleList = moduleList;
        this.classOfAcademy = classOfAcademy;
    }

    public Student(String name, String email, String password, List<Module> moduleList, ClassOfAcademy classOfAcademy) {
        super(name, email, password);
        this.moduleList = moduleList;
        this.classOfAcademy = classOfAcademy;
    }

    public Student(int id,String name, String email, String password, String url, Address address, LocalDate dob, Status status, List<Module> moduleList, ClassOfAcademy classOfAcademy) {
        super(id,name, email, password, url, address, dob, status);
        this.moduleList = moduleList;
        this.classOfAcademy = classOfAcademy;
    }


    public List<Module> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<Module> moduleList) {
        this.moduleList = moduleList;
    }

    public ClassOfAcademy getClassOfAcademy() {
        return classOfAcademy;
    }

    public void setClassOfAcademy(ClassOfAcademy classOfAcademy) {
        this.classOfAcademy = classOfAcademy;
    }
}
