package com.app.service.blogservice;

import com.app.model.Blog;
import com.app.service.IService;

import java.util.List;

public interface IBlogService extends IService<Blog> {
    List<Blog> findAllBlogsByClassId(int class_id);
}
