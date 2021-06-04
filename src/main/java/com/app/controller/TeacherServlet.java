package com.app.controller;

import com.app.model.Teacher;
import com.app.model.User;
import com.app.service.classService.ClassService;
import com.app.service.classService.IClassService;
import com.app.service.studentService.IStudentService;
import com.app.service.studentService.StudentService;
import com.app.service.teacherService.ITeacherService;
import com.app.service.teacherService.TeacherService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/Teacher")
public class TeacherServlet extends HttpServlet {
    ITeacherService teacherService = new TeacherService();
    IStudentService studentService = new StudentService();
    IClassService classService = new ClassService();
    static User teacherMain =  UserServlet.user;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String action = req.getParameter("action");
       if(action==null){
           action = "";
       }
       switch (action){
           default:
               teacherHomePage(req,resp);
               break;
       }


    }

    private void teacherHomePage(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("teacher",teacherMain);
        RequestDispatcher rd = req.getRequestDispatcher("/teacher/teacherHome.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
