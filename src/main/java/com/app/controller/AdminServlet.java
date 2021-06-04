package com.app.controller;



import com.app.model.*;
import com.app.model.ClassOfAcademy;
import com.app.service.addressService.AddressService;
import com.app.service.addressService.IAddressService;
import com.app.service.adminService.AdminService;
import com.app.service.adminService.IAdminService;
import com.app.service.classService.ClassService;
import com.app.service.classService.IClassService;
import com.app.service.courseService.CourseService;
import com.app.service.courseService.ICourseService;
import com.app.service.statusService.IStatusService;
import com.app.service.statusService.StatusService;
import com.app.service.studentService.IStudentService;
import com.app.service.studentService.StudentService;
import com.app.service.supervisorService.ISupervisorService;
import com.app.service.supervisorService.SupervisorService;
import com.app.service.teacherService.ITeacherService;
import com.app.service.teacherService.TeacherService;
//import com.app.service.addressService.AddressService;
//import com.app.service.addressService.IAddressService;
//import com.app.service.adminService.AdminService;
//import com.app.service.adminService.IAdminService;
//import com.app.service.classService.ClassService;
//import com.app.service.classService.IClassService;
//import com.app.service.courseService.CourseService;
//import com.app.service.courseService.ICourseService;
//import com.app.service.statusService.IStatusService;
//import com.app.service.statusService.StatusService;
//import com.app.service.supervisorService.ISupervisorService;
//import com.app.service.supervisorService.SupervisorService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/Admin")
public class AdminServlet extends HttpServlet {
    private static IAdminService adminService = new AdminService();
    private static ISupervisorService supervisorService = new SupervisorService();
    private static ICourseService courseService = new CourseService();
    private static IClassService classIService = new ClassService();
    private static IAddressService addressService = new AddressService();
    private static IStatusService statusService = new StatusService();
    private static ITeacherService teacherService = new TeacherService();
    private static IStudentService studentService = new StudentService();
    private static Admin adminMain = null;
    private static List<Supervisor> supervisorListMain = supervisorService.findAll();
    private static List<Address> addressListMain  = addressService.findAll();
    private static List<Course> courseListMain = courseService.findAll();
    private static List<Status> statusListMain = statusService.findAll();
    private static List<ClassOfAcademy> classListMain = classIService.findAll();
    private static List<Teacher> teacherListMain = teacherService.findAll();
    private static List<Student> studentListMain = studentService.findAll();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        if(action == null){
            action = "";
        }
        switch (action){
            case "home":
                adminHomePage(req,resp);
                break;
            case "createSupervisor":
                showFormSupervisorCreate(req,resp);
                break;
            case "editSupervisor":
                showFormSupervisorEdit(req,resp);
                break;
            case"deleteSupervisor":
                deleteSupervisor(req,resp);
                break;
            case "createTeacher":
                showFormTeacherCreate(req,resp);
                break;
            case "editTeacher":
                showFormTeacherEdit(req,resp);
                break;
            case"deleteTeacher":
                deleteTeacher(req,resp);
                break;
            case "createStudent":
                showFormStudentCreate(req,resp);
                break;
            case "editStudent":
                showFormStudentEdit(req,resp);
                break;
            case"deleteStudent":
                deleteStudent(req,resp);
                break;
            case "logOut":
                adminLoginPage(req,resp);
                break;
            default:
                adminLoginPage(req,resp);
                break;
        }
    }




    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println(action);
        if(action == null){
            action = "";
        }
        switch (action){
            case "home":
                adminHomePage(req,resp);
                break;
            case "login":
                loginAdmin(req,resp);
                break;
            case "createSupervisor":
                createSupervisor(req,resp);
                break;
            case "editSupervisor":
                updateSupervisor(req,resp);
                break;
            case "createTeacher":
                createTeacher(req,resp);
                break;
            case "editTeacher":
                updateTeacher(req,resp);
                break;
            case "createStudent":
                createStudent(req,resp);
                break;
            case "editStudent":
                editStudent(req,resp);
                break;

            default:
                adminLoginPage(req,resp);
                break;
        }
    }




    private void adminLoginPage(HttpServletRequest req, HttpServletResponse resp){
        try {
            resp.sendRedirect("/admin/adminLogin.jsp");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adminHomePage(HttpServletRequest req, HttpServletResponse resp) {
       req.setAttribute("admin",adminMain);
       req.setAttribute("supervisorList",supervisorListMain);
       req.setAttribute("teacherList",teacherListMain);
       req.setAttribute("studentList",studentListMain);

       RequestDispatcher rd = req.getRequestDispatcher("/admin/adminHome.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loginAdmin(HttpServletRequest req, HttpServletResponse resp) {
        List<Admin> adminList = adminService.findAll();
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        boolean check = false;
        for (Admin a: adminList
        ) {
            if(a.getEmail().equals(email)&& a.getPassword().equals(password)){
                    check = true;
                    req.setAttribute("admin",a);
                    adminMain = a;
                    adminHomePage(req,resp);
            }
        }
        if(!check){
            adminLoginPage(req,resp);
        }
    }
    private void showFormSupervisorEdit(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("addressList",addressListMain);
        req.setAttribute("statusList",statusListMain);
        int id = Integer.parseInt(req.getParameter("id"));
        Supervisor supervisor = supervisorService.findById(id);

        req.setAttribute("supervisor",supervisor);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/supervisorEdit.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormSupervisorCreate(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("addressList",addressListMain);
        req.setAttribute("statusList",statusListMain);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/supervisorCreate.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createSupervisor(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String url = req.getParameter("url");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        Address address = addressService.findById(Integer.parseInt(req.getParameter("address_id")));
        Status status = statusService.findById(Integer.parseInt(req.getParameter("status_id")));
        supervisorService.save(new Supervisor(name,email,password,url,address,dob,status));
        supervisorListMain = supervisorService.findAll();
        adminHomePage(req,resp);
    }
    private void updateSupervisor(HttpServletRequest req, HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String url = req.getParameter("url");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        Address address = addressService.findById(Integer.parseInt(req.getParameter("address_id")));
        Status status = statusService.findById(Integer.parseInt(req.getParameter("status_id")));
        Supervisor supervisor = new Supervisor(name,email,password,url,address,dob,status);
        supervisorService.edit(id,supervisor);
        supervisorListMain = supervisorService.findAll();
        adminHomePage(req,resp);
    }
    private void deleteSupervisor(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        supervisorService.delete(id);
        supervisorListMain = supervisorService.findAll();
        adminHomePage(req,resp);
    }


    private void deleteTeacher(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        teacherService.delete(id);
        teacherListMain = teacherService.findAll();
        adminHomePage(req,resp);

    }

    private void showFormTeacherEdit(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("addressList",addressListMain);
        req.setAttribute("statusList",statusListMain);
        int id = Integer.parseInt(req.getParameter("id"));
        Teacher teacher = teacherService.findById(id);

        req.setAttribute("teacher",teacher);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/teacherEdit.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showFormTeacherCreate(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("addressList",addressListMain);
        req.setAttribute("statusList",statusListMain);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/teacherCreate.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void createTeacher(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String url = req.getParameter("url");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        Address address = addressService.findById(Integer.parseInt(req.getParameter("address_id")));
        Status status = statusService.findById(Integer.parseInt(req.getParameter("status_id")));
        teacherService.save(new Teacher(name,email,password,url,address,dob,status));
        teacherListMain = teacherService.findAll();
        adminHomePage(req,resp);
    }
    private void updateTeacher(HttpServletRequest req, HttpServletResponse resp){
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String url = req.getParameter("url");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        Address address = addressService.findById(Integer.parseInt(req.getParameter("address_id")));
        Status status = statusService.findById(Integer.parseInt(req.getParameter("status_id")));
        Teacher teacher = new Teacher(name,email,password,url,address,dob,status);
        teacherService.edit(id,teacher);
        teacherListMain = teacherService.findAll();
        adminHomePage(req,resp);
    }

    private void showFormStudentEdit(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("addressList",addressListMain);
        req.setAttribute("statusList",statusListMain);
        req.setAttribute("classList",classListMain);
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentService.findById(id);
        System.out.println(student);
        req.setAttribute("student",student);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/studentEdit.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showFormStudentCreate(HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("addressList",addressListMain);
        req.setAttribute("statusList",statusListMain);
        req.setAttribute("classList",classListMain);
        RequestDispatcher rd = req.getRequestDispatcher("/admin/studentCreate.jsp");
        try {
            rd.forward(req,resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void editStudent(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String url = req.getParameter("url");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        Address address = addressService.findById(Integer.parseInt(req.getParameter("address_id")));
        Status status = statusService.findById(Integer.parseInt(req.getParameter("status_id")));
        ClassOfAcademy classOfAcademy = classIService.findById(Integer.parseInt(req.getParameter("class_id")));
        Student student = new Student(name,email,password,url,address,dob,status,classOfAcademy);
        studentService.edit(id,student);
        studentListMain = studentService.findAll();
        adminHomePage(req,resp);
    }

    private void createStudent(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String url = req.getParameter("url");
        LocalDate dob = LocalDate.parse(req.getParameter("dob"));
        Address address = addressService.findById(Integer.parseInt(req.getParameter("address_id")));
        Status status = statusService.findById(Integer.parseInt(req.getParameter("status_id")));
        ClassOfAcademy classOfAcademy = classIService.findById(Integer.parseInt(req.getParameter("class_id")));
        studentService.save(new Student(name,email,password,url,address,dob,status,classOfAcademy));
        studentListMain = studentService.findAll();
        adminHomePage(req,resp);
    }
    private void deleteStudent(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        studentService.delete(id);
        studentListMain = studentService.findAll();
        adminHomePage(req,resp);

    }



}
