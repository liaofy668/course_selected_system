package com.linzhy.coursesystem.service.impl;

import com.linzhy.coursesystem.mapper.CourseMapper;
import com.linzhy.coursesystem.mapper.StudentCourseMapper;
import com.linzhy.coursesystem.model.entity.Course;
import com.linzhy.coursesystem.model.entity.StudentCourse;
import com.linzhy.coursesystem.model.entity.Teacher;
import com.linzhy.coursesystem.mapper.TeacherMapper;
import com.linzhy.coursesystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    StudentCourseMapper studentCourseMapper;

    @Override
    public Teacher login(String userName, String password) {
        return teacherMapper.login(userName, password);
    }

    @Override
    public Integer getCount(String name, String departmentName) {
        return teacherMapper.getCountByDepOrName(name, departmentName);
    }

    @Override
    public List<Teacher> paginationQuery(Integer offset, String name, String departmentName) {

        return teacherMapper.paginationQuery(offset, name, departmentName);
    }

    @Override
    public void insertTeacher(Teacher teacher) {
        teacherMapper.insertTeacher(teacher);
    }

    @Override
    public Teacher getTeacherById(Integer teacherId) {
        return teacherMapper.selectById(teacherId);
    }

    @Override
    public Integer updateTeacher(Teacher teacher) {
        return teacherMapper.updateTeacher(teacher);
    }

    @Override
    public void deleteTeacherById(Integer teacherId) {
        teacherMapper.deleteById(teacherId);
    }

    @Override
    public List<Course> getTeacherCourseById(Integer teacherId) {
        Teacher teacher = teacherMapper.selectById(teacherId);
        if(teacher == null)return null;
        return courseMapper.selectByTeacherName(teacher.getTeacherName());
    }

    @Override
    public Integer getPageCount(Integer teacherId, String courseName, String studentName) {
        Teacher teacher = teacherMapper.selectById(teacherId);
        if(teacher == null)return 0;
        return studentCourseMapper.selectCount(teacher.getTeacherName(), courseName, studentName);
    }

    @Override
    public List<StudentCourse> getStudentCoursePage(Integer index, Integer pageSize, Integer teacherId, String courseName, String studentName) {
        Integer offset = (index-1)*pageSize;
        Teacher teacher = teacherMapper.selectById(teacherId);
        if(teacher == null)return null;
        return studentCourseMapper.paginationQuery(offset, pageSize, teacher.getTeacherName(), courseName, studentName);
    }

    @Override
    public StudentCourse getStudentCourseById(Integer scId) {
        return studentCourseMapper.selectById(scId);
    }

    @Override
    public Integer updateScore(Integer scId, Integer score) {
        return studentCourseMapper.updateScoreById(scId, score);
    }

    @Override
    public List<Course> getCourseListByTeacherName(String teacherName) {
        return courseMapper.selectByTeacherName(teacherName);
    }

    @Override
    public Integer insertCourse(Course newCourse) {
        return courseMapper.insertCourse(newCourse);
    }
}
