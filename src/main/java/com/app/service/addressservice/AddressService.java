package com.app.service.addressservice;

import com.app.config.ConnectionJDBC;
import com.app.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AddressService implements IAddressService {
    private Connection connection = ConnectionJDBC.getConnection();
    private String SELECT_ALL_ADDRESS = "select * from address;";
    private String SELECT_ADDRESS_BY_ID = "select * from address where id = ?;";
    private String SAVE_ADDRESS = "insert into address(name) value(?);";
    private String DELETE_ADDRESS = "delete from address where id = ?;";
    private String UPDATE_ADDRESS = "update address set name = ? where id = ?;";
    @Override
    public List<Address> findAll() {
        List<Address> addressList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ADDRESS);
            ResultSet resultSet = preparedStatement.executeQuery(SELECT_ALL_ADDRESS);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                addressList.add(new Address(id,name));
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return addressList;
    }

    @Override
    public Address findById(int id) {
        Address address = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ADDRESS_BY_ID);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int address_id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                address = new Address(address_id,name);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return address;
    }

    @Override
    public void save(Address p) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_ADDRESS);
            preparedStatement.setString(1,p.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ADDRESS);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void edit(int id, Address address) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ADDRESS);
            preparedStatement.setString(1,address.getName());
            preparedStatement.setInt(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

