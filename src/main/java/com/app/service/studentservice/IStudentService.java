package com.app.service.studentservice;

import com.app.model.Student;
import com.app.service.IService;

import java.util.List;

public interface IStudentService extends IService<Student> {
    List<Student> findStudentByClassId(int Class_id);
    double findScoreByStudentIModuleId(int student_id,int module_id);
    void updateScoreByStudentIModuleId(int student_id,int module_id,double score);
}
