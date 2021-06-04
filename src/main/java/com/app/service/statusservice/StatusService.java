package com.app.service.statusservice;

import com.app.config.ConnectionJDBC;
import com.app.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatusService implements IStatusService {
    public static final String SELECT_ALL_STT =
            "SELECT * FROM status;";

    public static final String SELECT_STT_BY_ID =
            "SELECT * FROM  status where status.id =?;";

    public static final String SAVE_STT =
            "INSERT INTO status ( name) VALUE(?);";

    public static final String DELETE_STT_BY_ID =
            "DELETE FROM status where status.id =?;";

    public static final String UPDATE_STT =
            "UPDATE status SET name =? WHERE status.id=?;";

    Connection connection = ConnectionJDBC.getConnection();

    @Override
    public List<Status> findAll(){
        List <Status> listStatus = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_STT);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                listStatus.add(new Status(id,name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listStatus;
    }

    @Override
    public Status findById(int id){
        Status status = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_STT_BY_ID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                status = new Status(id,name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return status;
    }

    @Override
    public void save(Status status){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SAVE_STT);
            statement.setString(1, status.getName());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_STT_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void edit(int id, Status status){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_STT);
            statement.setString(1, status.getName());
            statement.setInt(2,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
