package com.app.service.adminservice;

import com.app.config.ConnectionJDBC;
import com.app.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminService implements IAdminService {
    private Connection connection = ConnectionJDBC.getConnection();
    private String FIND_ALL_ADMIN = "select * from admin";

    @Override
    public List<Admin> findAll() {
        List<Admin> adminList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ADMIN);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                Admin admin = new Admin(id,email,password);
                adminList.add(admin);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return adminList;
    }

    @Override
    public Admin findById(int id) {
        return null;
    }

    @Override
    public void save(Admin p) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void edit(int id, Admin admin) {

    }
}
