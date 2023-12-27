package com.linzhy.coursesystem.service;

import com.linzhy.coursesystem.model.entity.Course;
import com.linzhy.coursesystem.model.entity.StudentCourse;
import com.linzhy.coursesystem.model.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher login(String userName, String password);

    Integer getCount(String name, String departmentName);

    List<Teacher> paginationQuery(Integer offset, String name, String departmentName);

    void insertTeacher(Teacher teacher);

    Teacher getTeacherById(Integer teacherId);

    Integer updateTeacher(Teacher teacher);

    void deleteTeacherById(Integer teacherId);

    List<Course> getTeacherCourseById(Integer teacherId);

    Integer getPageCount(Integer teacherId, String courseName, String studentName);

    List<StudentCourse> getStudentCoursePage(Integer index, Integer pageSize, Integer teacherId, String courseName, String studentName);

    StudentCourse getStudentCourseById(Integer scId);

    Integer updateScore(Integer scId, Integer score);

    List<Course> getCourseListByTeacherName(String teacherName);

    Integer insertCourse(Course newCourse);
}
