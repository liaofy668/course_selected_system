package com.linzhy.coursesystem.service;


import com.linzhy.coursesystem.model.entity.Course;

import java.util.List;
import java.util.Map;

public interface CourseService {
    Integer getCourseCount(String courseName, String teacherName);

    List<Course> paginationQuery(Integer PAGE_SIZE, Integer offset, String courseName, String teacherName);

    Course getCourseById(Integer courseId);

    Integer deleteCourseById(Integer courseId);
}
