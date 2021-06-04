package com.app.service.studentService;

import com.app.model.Student;
import com.app.service.IService;

import java.util.List;

public interface IStudentService extends IService<Student> {
    List<Student> findStudentByClassId(int Class_id);
}
