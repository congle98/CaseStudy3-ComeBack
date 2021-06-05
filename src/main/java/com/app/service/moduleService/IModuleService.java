package com.app.service.moduleService;

import com.app.model.Module;
import com.app.service.IService;

import java.util.List;

public interface IModuleService extends IService<Module> {
    List<Module> findByStudentId(int Student_id);
}
