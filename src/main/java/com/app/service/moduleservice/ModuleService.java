package com.app.service.moduleservice;

import com.app.config.ConnectionJDBC;
import com.app.model.Module;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ModuleService implements IModuleService {
    private static final String SELECT_ALL_MODULE = "SELECT * FROM module;";
    public static final String GET_MODULE_BY_ID = "select *from module where id = ?;";
    public static final String CREATE_MODULE = "insert into module(name, date_start, date_end) VALUES (?,?,?);";
    public static final String DELETE_MODULE = "delete from module where id = ?;";
    public static final String UPDATE_MODULE_WHERE_ID = "update module set name = ?, date_start = ?, date_end = ? where id = ?;";
    private static final String SELECT_MODULE_BY_STUDENT_ID = "select * from module join score_of_student sos on module.id = sos.module_id where sos.student_id = ?;";
    Connection connection = ConnectionJDBC.getConnection();

    @Override
    public List<Module> findAll() {
        List<Module> moduleList = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MODULE);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate date_start = resultSet.getDate("date_start").toLocalDate();
                LocalDate date_end = resultSet.getDate("date_end").toLocalDate();
                Module module = new Module(id,name,date_start,date_end);
                moduleList.add(module);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return moduleList;
    }

    @Override
    public Module findById(int id) {
        Module module = null;
        try {
            PreparedStatement statement = connection.prepareStatement(GET_MODULE_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int module_id = rs.getInt("id");
                String name = rs.getString("name");
                LocalDate date_start = rs.getDate("date_start").toLocalDate();
                LocalDate date_end = rs.getDate("date_end").toLocalDate();
                module = new Module(module_id,name,date_start,date_end);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return module;
    }

    @Override
    public void save(Module p) {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_MODULE);
            statement.setString(1,p.getName());
            statement.setDate(2, Date.valueOf(p.getDateStart()));
            statement.setDate(3, Date.valueOf(p.getDateEnd()));
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_MODULE);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public List<Module> findByStudentId(int Student_id){
        List<Module> moduleList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MODULE_BY_STUDENT_ID);
            preparedStatement.setInt(1,Student_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                LocalDate dateStart = resultSet.getDate(3).toLocalDate();
                LocalDate  dateEnd = resultSet.getDate(4).toLocalDate();
                Module module =new Module(id,name,dateStart,dateEnd);
                moduleList.add(module);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return moduleList;
    }
    @Override
    public void edit(int id, Module module) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_MODULE_WHERE_ID);
            statement.setString(1,module.getName());
            statement.setDate(2, Date.valueOf(module.getDateStart()));
            statement.setDate(3, Date.valueOf(module.getDateEnd()));
            statement.setInt(4,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
