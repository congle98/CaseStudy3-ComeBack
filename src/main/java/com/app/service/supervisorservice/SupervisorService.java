package com.app.service.supervisorservice;

import com.app.config.ConnectionJDBC;
import com.app.model.Address;
import com.app.model.Status;
import com.app.model.Supervisor;
import com.app.service.addressservice.AddressService;
import com.app.service.addressservice.IAddressService;
import com.app.service.statusservice.IStatusService;
import com.app.service.statusservice.StatusService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SupervisorService implements ISupervisorService {
    Connection connection = ConnectionJDBC.getConnection();
    IAddressService addressService = new AddressService();
    IStatusService statusService = new StatusService();

    private String SELECT_ALL_SUPERVISOR = "select * from supervisor;";
    private String SELECT_SUPERVISOR_BY_ID = "select * from supervisor where id = ?;";
    private String SAVE_SUPERVISOR = "insert into supervisor(name, email, password, address_id, url_img, dob, status_id) values(?,?,?,?,?,?,?);";
    private String DELETE_SUPERVISOR = "delete from supervisor where id = ?;";
    private String UPDATE_SUPERVISOR = "update supervisor set name = ?,email = ?, password = ?,address_id = ?, url_img = ?, dob = ?, status_id = ? where id =?;";
    private String SAVE_SUPERVISOR_ACCOUNT = "insert into supervisor(name,email,password) values(?,?,?);";
    @Override
    public List<Supervisor> findAll() {
        List<Supervisor> supervisorList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement =connection.prepareStatement(SELECT_ALL_SUPERVISOR);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){

                int address_id = resultSet.getInt("address_id");
                int status_id = resultSet.getInt("status_id");
                Address address = addressService.findById(address_id);
                Status status = statusService.findById(status_id);
                int Supervisor_id = resultSet.getInt("id");
                String Supervisor_name = resultSet.getString("name");
                String Supervisor_email = resultSet.getString("email");
                String Supervisor_password = resultSet.getString("password");
                String Supervisor_img = resultSet.getString("url_img");
                LocalDate Supervisor_dob = resultSet.getDate("dob").toLocalDate();
                Supervisor supervisor = new Supervisor(Supervisor_id,Supervisor_name,Supervisor_email,Supervisor_password,
                        Supervisor_img,address,Supervisor_dob,status);
                supervisorList.add(supervisor);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return supervisorList;
    }

    @Override
    public Supervisor findById(int id) {
        Supervisor supervisor = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_SUPERVISOR_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int address_id = resultSet.getInt("address_id");
                int status_id = resultSet.getInt("status_id");
                Address address = addressService.findById(address_id);
                Status status = statusService.findById(status_id);
                int Supervisor_id = resultSet.getInt("id");
                String Supervisor_name = resultSet.getString("name");
                String Supervisor_email = resultSet.getString("email");
                String Supervisor_password = resultSet.getString("password");
                String Supervisor_img = resultSet.getString("url_img");
                LocalDate Supervisor_dob = resultSet.getDate("dob").toLocalDate();
                supervisor = new Supervisor(Supervisor_id,Supervisor_name,Supervisor_email,Supervisor_password,
                         Supervisor_img,address,Supervisor_dob,status);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return supervisor;

    }

    @Override
    public void save(Supervisor p) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SUPERVISOR);
            preparedStatement.setString(1,p.getName());
            preparedStatement.setString(2,p.getEmail());
            preparedStatement.setString(3,p.getPassword());
            preparedStatement.setInt(4,p.getAddress().getId());
            preparedStatement.setString(5,p.getUrl());
            preparedStatement.setDate(6, Date.valueOf(p.getDob()));
            preparedStatement.setInt(7,p.getStatus().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SUPERVISOR);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void edit(int id, Supervisor supervisor) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SUPERVISOR);
            preparedStatement.setString(1,supervisor.getName());
            preparedStatement.setString(2,supervisor.getEmail());
            preparedStatement.setString(3,supervisor.getPassword());
            preparedStatement.setInt(4,supervisor.getAddress().getId());
            preparedStatement.setString(5,supervisor.getUrl());
            preparedStatement.setDate(6, Date.valueOf(supervisor.getDob()));
            preparedStatement.setInt(7,supervisor.getStatus().getId());
            preparedStatement.setInt(8,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
