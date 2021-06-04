package com.app.controller;

import com.app.model.Supervisor;
import com.app.service.supervisorservice.ISupervisorService;
import com.app.service.supervisorservice.SupervisorService;

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
                break;
            case "supervisor":
                supervisorLogin(req,resp);
                break;
            case "teacher":
                break;
            default:
                comeBackIndex(req,resp);
                break;

        }
    }
    private void supervisorLogin(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher rd =req.getRequestDispatcher("/supervisor/supervisorHome.jsp");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        List<Supervisor> supervisorList = null;
        try {
            supervisorList = supervisorService.findAll();
            System.out.println(supervisorList);
            boolean check = false;
            for (Supervisor s: supervisorList
            ) {
                if(s.getEmail().equals(email)&& s.getPassword().equals(password)) {
                    check = true;

                    try {
                        req.setAttribute("supervisor",s);
                        rd.forward(req,resp);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ServletException e) {
                        e.printStackTrace();
                    }
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
