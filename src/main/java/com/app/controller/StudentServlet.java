package com.app.controller;

import com.app.service.classservice.ClassService;
import com.app.service.classservice.IClassService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "StudentServlet", value = "/student")
public class StudentServlet extends HttpServlet {
   IClassService classService = new ClassService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        try {
            if(action == null) action = "";
            switch (action){
            default:
                    showAllStataus(req,resp);
                break;
            }
        } catch (SQLException throwables) {
                throwables.printStackTrace();
        }
    }

    private void showAllStataus(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("check.jsp");
        req.setAttribute("listSTT", classService.findById(1));
        dispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
