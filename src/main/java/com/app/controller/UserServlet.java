package com.app.controller;

import com.app.model.Student;
import com.app.model.Supervisor;
import com.app.model.Teacher;
import com.app.model.User;
import com.app.service.studentservice.IStudentService;
import com.app.service.studentservice.StudentService;
import com.app.service.supervisorservice.ISupervisorService;
import com.app.service.supervisorservice.SupervisorService;
import com.app.service.teacherservice.ITeacherService;
import com.app.service.teacherservice.TeacherService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/trang-chu")
public class UserServlet extends HttpServlet {

    ISupervisorService supervisorService = new SupervisorService();
    ITeacherService teacherService = new TeacherService();
    IStudentService studentService = new StudentService();
    static User user = null;
    static Student studentUser = null;
    static Supervisor supervisorUser = null;
     @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null){
            action = "";
        }
        switch (action){
            default:
                comeBackIndex(req,resp);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");


        if(action == null){
            action = "";
        }

        switch (action){

            case "student":
                studentLogin(req,resp);
                break;
            case "supervisor":
                supervisorLogin(req,resp);
                break;
            case "teacher":
                teacherLogin(req,resp);
                break;
            default:
                comeBackIndex(req,resp);
                break;

        }
    }

    private void studentLogin(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher rd = req.getRequestDispatcher("/student/studentHome.jsp");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        List<Student> studentList = null;
        try {
            studentList = studentService.findAll();

            boolean check = false;
            for (Student s: studentList
            ) {
                if(s.getEmail().equals(email)&& s.getPassword().equals(password)) {
                    check = true;
                    studentUser = s;
                    StudentServlet.studentMain = studentUser;
                    req.setAttribute("student",studentUser);
                    rd.forward(req,resp);
                }
            }
            if (!check){
                comeBackIndex(req,resp);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

    private void teacherLogin(HttpServletRequest req, HttpServletResponse resp) {
         RequestDispatcher rd = req.getRequestDispatcher("/teacher/teacherHome.jsp");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        List<Teacher> teacherList = null;
        try {
            teacherList = teacherService.findAll();

            boolean check = false;
            for (Teacher t: teacherList
            ) {
                if(t.getEmail().equals(email)&& t.getPassword().equals(password)) {
                    check = true;
                        user = t;
                        TeacherServlet.teacherMain = user;

                        req.setAttribute("teacher",user);
                        rd.forward(req,resp);
                }
            }
            if (!check){
                comeBackIndex(req,resp);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

    private void supervisorLogin(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher rd = req.getRequestDispatcher("/teacher/teacherHome.jsp");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        List<Supervisor> supervisorList = null;
        try {
            supervisorList =  supervisorService.findAll();

            boolean check = false;
            for (Supervisor s: supervisorList
            ) {
                if(s.getEmail().equals(email)&& s.getPassword().equals(password)) {
                    check = true;
                    supervisorUser = s;
                    SupervisorServlet.supervisorMain = supervisorUser;

                    req.setAttribute("supervisor",supervisorUser);
                    rd.forward(req,resp);
                }
            }
            if (!check){
                comeBackIndex(req,resp);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }

    }

    private void comeBackIndex(HttpServletRequest req, HttpServletResponse resp){
        try {
            resp.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
