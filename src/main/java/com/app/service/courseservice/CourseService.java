package com.app.service.courseservice;

import com.app.config.ConnectionJDBC;
import com.app.model.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseService implements ICourseService {
    public static final String SELECT_ALL_COURSE =
            "SELECT * FROM course;";

    public static final String SELECT_COURSE_BY_ID =
            " SELECT * FROM course where course.id =?;";

    public static final String SAVE_COURSE =
            "INSERT INTO course(name, price) VALUE(?,?);";

    private static final String DELETE_COURSE_BY_ID =
            " DELETE FROM course where course.id =?;";

    private static final String UPDATE_COURSE_BY_ID =
            "UPDATE course SET name=?, price=? WHERE course.id=?";

    Connection connection = ConnectionJDBC.getConnection();

    @Override
    public List<Course> findAll() {
        List<Course> listCourse = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_ALL_COURSE);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                listCourse.add(new Course(id,name,price));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return listCourse;
    }

    @Override
    public Course findById(int id)  {
        Course course = null;
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SELECT_COURSE_BY_ID);    statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                course = new Course(id,name,price);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return course;
    }

    @Override
    public void save(Course course) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SAVE_COURSE);
            statement.setString(1, course.getName());
            statement.setDouble(2, course.getPrice());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Override
    public void delete(int id){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE_COURSE_BY_ID);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void edit(int id, Course course){
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE_COURSE_BY_ID);     statement.setString(1, course.getName());
            statement.setDouble(2, course.getPrice());
            statement.setInt(3,id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}