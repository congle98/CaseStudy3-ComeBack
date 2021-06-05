package com.app.controller;

import com.app.model.*;
import com.app.service.addressservice.AddressService;
import com.app.service.addressservice.IAddressService;
import com.app.service.blogservice.BlogService;
import com.app.service.blogservice.IBlogService;
import com.app.service.classservice.ClassService;
import com.app.service.classservice.IClassService;
import com.app.service.statusservice.IStatusService;
import com.app.service.statusservice.StatusService;
import com.app.service.studentservice.IStudentService;
import com.app.service.studentservice.StudentService;
import com.app.service.teacherservice.ITeacherService;
import com.app.service.teacherservice.TeacherService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(value = "/Teacher")
public class TeacherServlet extends HttpServlet {
    ITeacherService teacherService = new TeacherService();
    IStudentService studentService = new StudentService();
    IClassService classService = new ClassService();
    IAddressService addressService = new AddressService();
    IStatusService statusService = new StatusService();
    IBlogService blogService = new BlogService();
    private  List<Address> addressListMain  = addressService.findAll();
    static  User teacherMain =  UserServlet.user;
    List<ClassOfAcademy> classListMain = classService.findClassByTeacherId(teacherMain.getId());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String action = req.getParameter("action");
       if(action==null){
           action = "";
       }
       switch (action){
           case "classManager":
               showFormClass(req,resp);
               break;
           case "logOut":
               LoginPage(req,resp);
               break;
           case "showStudents":
               showFormStudent(req,resp);
               break;
           case "accountManager":
               showFormAccount(req,resp);
               break;
           case "home":
               teacherHomePage(req,resp);
               break;
           case "showBlogs":
               showFormBlog(req,resp);
               break;
           case "createBlog":
               showFormCreateBlog(req,resp);
               break;

           default:
               teacherHomePage(req,resp);
               break;
       }
    }



    private void showFormBlog(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        List<Blog> blogList = blogService.findAllBlogsByClassId(id);
        req.setAttribute("blogList",blogList);
        req.setAttribute("teacher",teacherMain);
        RequestDispatcher rd = req.getRequestDispatcher("/teacher/blogManager.jsp");
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
        String action = req.getParameter("action");
        if(action==null){
            action = "";
        }
        switch (action){
            case "accountManager":
                accountManager(req,resp);
                break;
            case "createBlog":
                createBlog(req,resp);
                break;
            default:
                teacherHomePage(req,resp);
                break;
        }

    }




    private void showFormStudent(HttpServletRequest req, HttpServletResponse resp) {
        int class_id = Integer.parseInt(req.getParameter("id"));
        List<Student> studentList = studentService.findStudentByClassId(class_id);
        req.setAttribute("teacher",teacherMain);
        req.setAttribute("studentList",studentList);
        RequestDispatcher rd = req.getRequestDispatcher("/teacher/studentList.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    private void LoginPage(HttpServletRequest req, HttpServletResponse resp) {
        try {
            resp.sendRedirect("/index.jsp");
        } catch (IOException e) {
            e.printStackTrace();
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
    private void showFormClass(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher rd = req.getRequestDispatcher("/teacher/classManager.jsp");
        req.setAttribute("teacher",teacherMain);
        req.setAttribute("classList",classListMain);
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showFormAccount(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("teacher",teacherMain);
        req.setAttribute("addressList",addressListMain);
        RequestDispatcher rd = req.getRequestDispatcher("/teacher/accountManager.jsp");
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
        Status status = statusService.findById(Integer.parseInt(req.getParameter("status_id")));
        Teacher teacher = new Teacher(id,name,email,password,url,address,dob,status);
        teacherService.edit(teacher.getId(),teacher);
        teacherMain = teacher;
        teacherHomePage(req,resp);
    }
    private void showFormCreateBlog(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("teacher",teacherMain);
        req.setAttribute("classList",classListMain);
        RequestDispatcher rd = req.getRequestDispatcher("/teacher/blogCreate.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createBlog(HttpServletRequest req, HttpServletResponse resp) {
        String description = req.getParameter("description");
        LocalDate localDate = LocalDate.parse(req.getParameter("date"));
        int class_id = Integer.parseInt(req.getParameter("class_id"));
        ClassOfAcademy classOfAcademy = classService.findById(class_id);
        Blog blog = new Blog(description,localDate,classOfAcademy);
        blogService.save(blog);
        showFormClass(req,resp);

    }

}
