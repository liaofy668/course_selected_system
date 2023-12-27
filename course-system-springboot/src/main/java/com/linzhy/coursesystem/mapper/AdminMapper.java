package com.linzhy.coursesystem.mapper;

import com.linzhy.coursesystem.model.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper {
    Admin login(String userName, String password);
}
