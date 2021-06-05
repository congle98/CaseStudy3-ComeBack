package com.app.service.classservice;

import com.app.model.ClassOfAcademy;
import com.app.service.IService;

import java.util.List;

public interface IClassService extends IService<ClassOfAcademy> {
    List<ClassOfAcademy> findClassByTeacherId(int teacher_id);
}
