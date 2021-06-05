package com.app.service.teacherservice;

import com.app.config.ConnectionJDBC;
import com.app.model.Address;
import com.app.model.Status;
import com.app.model.Teacher;
import com.app.service.addressservice.AddressService;
import com.app.service.addressservice.IAddressService;
import com.app.service.statusservice.IStatusService;
import com.app.service.statusservice.StatusService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TeacherService implements ITeacherService {
    public static final String GET_ALL_TEACHER = "select * from teacher;";
    public static final String GET_TEACHER_BY_ID = "select * from teacher where id = ?;";
    public static final String CREATE_TEACHER = "insert into teacher (name, email, password, url_img, address_id, dob, status_id) VALUES (?,?,?,?,?,?,?);";
    public static final String DETELE_TEACHER_WHERE_ID = "delete from teacher where id = ?;";
    public static final String UPDATE_TEACHER_WHERE_ID = "update teacher set name = ?, email = ?, password = ?, url_img = ?,  address_id = ?, dob = ?, status_id = ? where  id = ?;";

    Connection connection = ConnectionJDBC.getConnection();
    IAddressService addressService = new AddressService();
    IStatusService statusService = new StatusService();
    @Override
    public List<Teacher> findAll() {
        List<Teacher> teachers = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_TEACHER);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String url_img = rs.getString("url_img");
                int address_id = rs.getInt("address_id");
                Address address = addressService.findById(address_id);
                LocalDate dob = rs.getDate("dob").toLocalDate();
                int status_id = rs.getInt("status_id");
                Status status = statusService.findById(status_id);
                Teacher teacher = new Teacher(id,name,email,password,url_img,address,dob,status);
                teachers.add(teacher);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return teachers;
    }

    @Override
    public Teacher findById(int id) {
        Teacher teacher = null;
        try {
            PreparedStatement statement = connection.prepareStatement(GET_TEACHER_BY_ID);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                int teacher_id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String password = rs.getString("password");
                String url_img = rs.getString("url_img");
                int address_id = rs.getInt("address_id");
                Address address = addressService.findById(address_id);
                LocalDate dob = rs.getDate("dob").toLocalDate();
                int status_id = rs.getInt("status_id");
                Status status = statusService.findById(status_id);
                teacher = new Teacher(teacher_id,name,email,password,url_img,address,dob,status);
                System.out.println(teacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return teacher;
    }

    @Override
    public void save(Teacher p) {
        try {
            PreparedStatement statement = connection.prepareStatement(CREATE_TEACHER);
            statement.setString(1,p.getName());
            statement.setString(2,p.getEmail());
            statement.setString(3,p.getPassword());
            statement.setString(4,p.getUrl());
            statement.setInt(5,p.getAddress().getId());
            statement.setDate(6, Date.valueOf(p.getDob()));
            statement.setInt(7,p.getStatus().getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DETELE_TEACHER_WHERE_ID);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void edit(int id, Teacher teacher) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_TEACHER_WHERE_ID);
            statement.setString(1,teacher.getName());
            statement.setString(2,teacher.getEmail());
            statement.setString(3,teacher.getPassword());
            statement.setString(4,teacher.getUrl());
            statement.setInt(5,teacher.getAddress().getId());
            statement.setDate(6, Date.valueOf(teacher.getDob()));
            statement.setInt(7,teacher.getStatus().getId());
            statement.setInt(8,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
