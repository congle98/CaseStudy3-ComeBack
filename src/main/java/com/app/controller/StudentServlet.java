package com.app.controller;

import com.app.model.*;
import com.app.service.addressservice.AddressService;
import com.app.service.addressservice.IAddressService;
import com.app.service.classservice.ClassService;
import com.app.service.classservice.IClassService;
import com.app.service.moduleservice.IModuleService;
import com.app.service.moduleservice.ModuleService;
import com.app.service.statusservice.IStatusService;
import com.app.service.statusservice.StatusService;
import com.app.service.studentservice.IStudentService;
import com.app.service.studentservice.StudentService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet(name = "StudentServlet", value = "/Student")
public class StudentServlet extends HttpServlet {
   IClassService classService = new ClassService();
   IStatusService statusService = new StatusService();
   IAddressService addressService = new AddressService();
   IModuleService moduleService = new ModuleService();
   IStudentService studentService = new StudentService();
   static Student studentMain = UserServlet.studentUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "logOut":
                LoginPage(req,resp);
                break;
            case "accountManager":
                showFormAccount(req,resp);
                break;
            case "moduleList":
                showFormModule(req,resp);
                break;
            case "showScore":
                showFormScore(req,resp);
            case "home":
                studentHomePage(req,resp);
                break;
            default:
                studentHomePage(req,resp);
                break;
        }

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "accountManager":
                accountManager(req,resp);
                break;
            default:
                studentHomePage(req,resp);
                break;
        }

    }

    private void LoginPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormModule(HttpServletRequest req, HttpServletResponse resp) {
        List<Module> moduleList = moduleService.findByStudentId(studentMain.getId());
        System.out.println(moduleList);
        req.setAttribute("moduleList",moduleList);
        req.setAttribute("student",studentMain);
        RequestDispatcher rd = req.getRequestDispatcher("/student/moduleList.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void studentHomePage(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("student",studentMain);
        RequestDispatcher rd = req.getRequestDispatcher("/student/studentHome.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormAccount(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("student",studentMain);
        List<Address> addressList = addressService.findAll();
        List<Status> statusList = statusService.findAll();
        List<ClassOfAcademy> classList = classService.findAll();
        req.setAttribute("addressList",addressList);
        req.setAttribute("statusList",statusList);
        req.setAttribute("classList",classList);
        RequestDispatcher rd = req.getRequestDispatcher("/student/accountManager.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void accountManager(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String url = req.getParameter("url");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        Address address = addressService.findById(Integer.parseInt(req.getParameter("address_id")));
        ClassOfAcademy classOfAcademy = classService.findById(Integer.parseInt(req.getParameter("class_id")));
        Status status = statusService.findById(Integer.parseInt(req.getParameter("status_id")));
        Student student = new Student(id,name,email,password,url,address,dob,status,classOfAcademy);
        studentService.edit(student.getId(),student);
        studentMain = student;
        studentHomePage(req,resp);
    }
    private void showFormScore(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("student",studentMain);
        int module_id = Integer.parseInt(req.getParameter("id"));
        Module module = moduleService.findById(module_id);
        req.setAttribute("module",module);
        double score = studentService.findScoreByStudentIModuleId(studentMain.getId(),module_id);
        req.setAttribute("score",score);
        RequestDispatcher rd = req.getRequestDispatcher("/student/viewScore.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
