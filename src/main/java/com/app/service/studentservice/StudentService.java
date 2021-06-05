package com.app.service.studentservice;

import com.app.config.ConnectionJDBC;
import com.app.model.*;
import com.app.model.ClassOfAcademy;
import com.app.service.addressservice.AddressService;
import com.app.service.addressservice.IAddressService;
import com.app.service.classservice.ClassService;
import com.app.service.classservice.IClassService;
import com.app.service.moduleservice.IModuleService;
import com.app.service.moduleservice.ModuleService;
import com.app.service.statusservice.IStatusService;
import com.app.service.statusservice.StatusService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class StudentService implements IStudentService {
    private static final String SELECT_ALL_STUDENT = "SELECT * FROM student;";
    private static final String SELECT_MODULE_BY_STUDENT_ID_1 = "select module_id from student_module where student_id=?;";
    private static final String SELECT_FROM_STUDENT_WHERE_STUDENT_ID = "SELECT * FROM student WHERE student.id=?;";
    private static final String INSERT_STUDENT = "INSERT INTO student(name, email, password, address_id, class_id, dob, url_img, status_id ) VALUE (?,?,?,?,?,?,?,?);\n;";
    private static final String DELETE_STUDENT = "delete from student where id = ?;";
    private static final String UPDATE_STUDENT_BY_ID = "UPDATE student SET name=?, email=?, password=?, address_id=?, class_id=?, dob=?, url_img=?, status_id=? WHERE student.id =?;";
    private static final String SELECT_ALL_STUDENT_BY_CLASS_ID = "select * from student where class_id = ?;";
    private static final String SELECT_ALL_STUDENT_BY_MODULE_ID = "select * from student join score_of_student sos on student.id = sos.student_id where sos.module_id = ?;";
    private static final String SELECT_SCORE_BY_STUDENT_ID_MODULE_ID = "select score from score_of_student where student_id = ? and module_id = ?;";
    Connection connection = ConnectionJDBC.getConnection();
    IAddressService addressService = new AddressService();
    IClassService classService = new ClassService();
    IStatusService statusService = new StatusService();
    IModuleService moduleServices = new ModuleService();

    @Override
    public List<Student> findAll() {
        List<Student>  listStudent= new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_STUDENT);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String pass = resultSet.getString("password");
                Address address = addressService.findById(resultSet.getInt("address_id"));
                ClassOfAcademy cl = classService.findById(resultSet.getInt("class_id"));
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String img = resultSet.getString("url_img");
                Status status = statusService.findById(resultSet.getInt("status_id"));
                List<Module> moduleList = moduleServices.findByStudentId(id);
                 Student student = new Student(id, name, email, pass,img,  address, dob, status,moduleList, cl);
                listStudent.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listStudent;
    }

    public List<Module> findModule(int id){
        List<Module> listMD = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_MODULE_BY_STUDENT_ID_1);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Module module = moduleServices.findById(resultSet.getInt("module_id"));
                listMD.add(module);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        for (Module a: listMD
        ) {
            System.out.println(a);
        }
        return listMD ;
    }

    @Override
    public Student findById(int id) {
        Student student = null;
        try {
            PreparedStatement statement = connection.prepareStatement(SELECT_FROM_STUDENT_WHERE_STUDENT_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id_ = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String pass = resultSet.getString("password");
                Address address = addressService.findById(resultSet.getInt("address_id"));
                ClassOfAcademy cl = classService.findById(resultSet.getInt("class_id"));
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String img = resultSet.getString("url_img");
                Status status = statusService.findById(resultSet.getInt("status_id"));
                List<Module> moduleList = moduleServices.findByStudentId(id);
                student = new Student(id_, name, email, pass,img,  address, dob, status,moduleList, cl);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> findStudentByClassId(int Class_id) {
        List<Student> studentList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT_BY_CLASS_ID);
            preparedStatement.setInt(1,Class_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String pass = resultSet.getString("password");
                Address address = addressService.findById(resultSet.getInt("address_id"));
                ClassOfAcademy cl = classService.findById(resultSet.getInt("class_id"));
                LocalDate dob = resultSet.getDate("dob").toLocalDate();
                String img = resultSet.getString("url_img");
                Status status = statusService.findById(resultSet.getInt("status_id"));
                List<Module> moduleList = moduleServices.findByStudentId(id);
                Student student = new Student(id, name, email, pass,img,  address, dob, status,moduleList, cl);
                studentList.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return studentList;
    }

    @Override
    public void save(Student p) {
        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_STUDENT);
            statement.setString(1, p.getName());
            statement.setString(2, p.getEmail());
            statement.setString(3, p.getPassword());
            statement.setInt(4, p.getAddress().getId());
            statement.setInt(5, p.getClassOfAcademy().getId());
            statement.setDate(6, Date.valueOf(p.getDob()));
            statement.setString(7, p.getUrl());
            statement.setInt(8, p.getStatus().getId());
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_STUDENT);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public double findScoreByStudentIModuleId(int student_id,int module_id){
        double score = 0;
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_SCORE_BY_STUDENT_ID_MODULE_ID);
            preparedStatement.setInt(1,student_id);
            preparedStatement.setInt(2,module_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                score =resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
      return score;
    }
    public  void updateScoreByStudentIModuleId(int student_id,int module_id,double score){
        String delete = "delete from score_of_student where student_id = ? and module_id = ?;";
        String update = "insert into score_of_student(student_id,module_id,score) values(?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.setInt(1,student_id);
            preparedStatement.setInt(2,module_id);
            preparedStatement.executeUpdate();
            PreparedStatement preparedStatement1 = connection.prepareStatement(update);
            preparedStatement1.setInt(1,student_id);
            preparedStatement1.setInt(2,module_id);
            preparedStatement1.setDouble(3,score);
            preparedStatement1.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @Override
    public void edit(int id, Student student) {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_STUDENT_BY_ID);
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPassword());
            statement.setInt(4, student.getAddress().getId());
            statement.setInt(5, student.getClassOfAcademy().getId());
            statement.setDate(6, Date.valueOf(student.getDob()));
            statement.setString(7, student.getUrl());
            statement.setInt(8, student.getStatus().getId());
            statement.setInt(9, id);
            statement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
