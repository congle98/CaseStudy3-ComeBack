package com.app.service.moduleservice;

import com.app.config.ConnectionJDBC;
import com.app.model.Module;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModuleService implements IModuleService {

    private static final String SELECT_ALL_MODULE = "SELECT * FROM module;";
    Connection connection = ConnectionJDBC.getConnection();

    @Override
    public List<Module> findAll() {
        List<Module> moduleList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MODULE);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    @Override
    public Module findById(int id) {
        return null;
    }

    @Override
    public void save(Module p) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void edit(int id, Module module) {

    }
}
