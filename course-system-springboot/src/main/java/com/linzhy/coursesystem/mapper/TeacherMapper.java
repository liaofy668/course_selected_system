package com.linzhy.coursesystem.mapper;

import com.linzhy.coursesystem.model.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {

    Teacher login(String userName, String password);

    Integer getCountByDepOrName(String name, String departmentName);

    List<Teacher> paginationQuery(Integer offset, String name, String departmentName);

    int insertTeacher(Teacher new_teacher);

    Teacher selectById(Integer teacherId);

    Integer updateTeacher(Teacher teacher);

    int deleteById(Integer teacherId);

    Teacher selectByName(String teacherName);
}
