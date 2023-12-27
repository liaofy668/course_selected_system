package com.linzhy.coursesystem.service.impl;

import com.linzhy.coursesystem.model.entity.Teacher;
import com.linzhy.coursesystem.mapper.CourseMapper;
import com.linzhy.coursesystem.mapper.TeacherMapper;
import com.linzhy.coursesystem.model.entity.Course;
import com.linzhy.coursesystem.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public Integer getCourseCount(String courseName, String teacherName) {
//        Teacher teacher = teacherMapper.selectByName(teacherName);
        return courseMapper.getCount(courseName, teacherName);
    }

    @Override
    public List<Course> paginationQuery(Integer PAGE_SIZE, Integer offset, String courseName, String teacherName) {
        return courseMapper.paginationQuery(PAGE_SIZE, offset,courseName, teacherName );
    }

    @Override
    public Course getCourseById(Integer courseId) {
        return courseMapper.getById(courseId);
    }

    @Override
    public Integer deleteCourseById(Integer courseId) {
        return courseMapper.deleteById(courseId);
    }

}
