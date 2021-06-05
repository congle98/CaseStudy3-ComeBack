package com.app.service.moduleservice;

import com.app.model.Module;
import com.app.service.IService;

import java.util.List;

public interface IModuleService extends IService<Module> {
    List<Module> findByStudentId(int Student_id);
}
