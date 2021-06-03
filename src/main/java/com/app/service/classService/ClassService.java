
        package com.app.service.classService;
        import com.app.config.ConnectionJDBC;
        import com.app.model.ClassOfAcademy;
        import com.app.model.Course;
        import com.app.model.Teacher;
        import com.app.service.courseService.CourseService;
        import com.app.service.IService;
        import com.app.service.courseService.ICourseService;
        import com.app.service.teacherService.ITeacherService;
        import com.app.service.teacherService.TeacherService;

        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.util.ArrayList;
        import java.util.List;

        public class ClassService implements IClassService {
            private static final String SELECT_ALL_CLASS = "SELECT * FROM class;";
            private static final String SELECT_CLASS_BY_ID = "SELECT * FROM class WHERE class.id =?;";
            private static final String INSERT_CLASS = "INSERT INTO class (name, teacher_id , course_id) VALUE (?,?,?);";
            private static final String DELETE_CLASS_BY_ID = "DELETE FROM class WHERE class.id =?;";
            private static final String UPDATE_CLASS_BY_ID = "UPDATE class SET name =?, teacher_id =?, course_id =? WHERE class.id=?;";
            ICourseService courService = new CourseService();
            ITeacherService teacherService = new TeacherService();
            Connection connection = ConnectionJDBC.getConnection();
            @Override
            public List<ClassOfAcademy> findAll() {
                List<ClassOfAcademy> listClassOfAcademies = new ArrayList<>();
                try {
                    PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CLASS);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()){
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        int idTeacher = resultSet.getInt("teacher_id");
                        int idCourse = resultSet.getInt("course_id");
                        Teacher teacher = teacherService.findById(idTeacher);
                        Course course = courService.findById(idCourse);
                        ClassOfAcademy classOfAcademy1 = new ClassOfAcademy(id,name,teacher,course); System.out.println("loi cmnr");
                        listClassOfAcademies.add(classOfAcademy1);
                        for (ClassOfAcademy a: listClassOfAcademies
                        ) {
                            System.out.println(a);
                        }
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return listClassOfAcademies;
            }

            @Override
            public ClassOfAcademy findById(int id) {
                ClassOfAcademy classOfAcademyById = new ClassOfAcademy();
                try {
                    PreparedStatement statement = connection.prepareStatement(SELECT_CLASS_BY_ID);
                    statement.setInt(1,id);
                    ResultSet resultSet = statement.executeQuery();
                    while (resultSet.next()){
                        String name = resultSet.getString("name");
                        int idTeacher = resultSet.getInt("teacher_id");
                        int idCourse = resultSet.getInt("course_id");
                        Teacher teacher = teacherService.findById(idTeacher);
                        Course course = courService.findById(idCourse);
                        classOfAcademyById = new ClassOfAcademy(id,name,teacher,course);

                    }        } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                return classOfAcademyById;
            }

            @Override
            public void save(ClassOfAcademy p) {
                try {
                    PreparedStatement statement = connection.prepareStatement(INSERT_CLASS);
                    statement.setString(1, p.getName());
                    statement.setInt(2, p.getTeacher().getId());
                    statement.setInt(3, p.getCourse().getId());
                    statement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            @Override
            public void delete(int id) {
                try {
                    PreparedStatement statement = connection.prepareStatement(DELETE_CLASS_BY_ID);
                    statement.setInt(1, id);
                    statement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }

            @Override
            public void edit(int id, ClassOfAcademy ClassOfAcademy) {
                try {
                    PreparedStatement statement = connection.prepareStatement(UPDATE_CLASS_BY_ID);
                    statement.setString(1, ClassOfAcademy.getName());
                    statement.setInt(2, ClassOfAcademy.getTeacher().getId());
                    statement.setInt(3, ClassOfAcademy.getCourse().getId());
                    statement.setInt(4, id);
                    statement.executeUpdate();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
