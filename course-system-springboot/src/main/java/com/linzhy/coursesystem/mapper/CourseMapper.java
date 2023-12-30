package com.linzhy.coursesystem.mapper;


import com.linzhy.coursesystem.model.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {
    Integer getCount(String courseName, String teacherName);

    List<Course> paginationQuery(Integer pageSize, Integer offset, String courseName, String teacherName);

    Course getById(Integer courseId);

    Integer deleteById(Integer courseId);

    List<Course> selectByTeacherName(String teacherName);

    Integer insertCourse(Course newCourse);

    Integer selectCourseIdByCourseNameAndTeacherName(String courseName, String teacherName);
}
