package com.linzhy.coursesystem.service;

import com.linzhy.coursesystem.model.entity.Student;
import com.linzhy.coursesystem.model.vo.SelectedCourse;

import java.util.List;
import java.util.Map;

public interface StudentService {
    List<Student> list();

    Student login(String userName, String password);

    Integer getPageCount(String studentName, String className, Integer pageSize);

    List<Student> paginationQuery(Integer offset, String studentName, String className);

    Integer insertStudent(Student student);

    Student getStudentById(Integer studentId);

    Integer updateStudent(Student student);

    Integer deleteStudent(Integer studentId);

    List<Map<String, Object>> getCoursePage(Integer pageSize, Integer offset, String studentName, String courseName, String teacherName);

    Integer selectCourse(String studentName, Integer courseId);

    List<SelectedCourse> getStudentCourse(String studentName);

    Integer unselectCourse(String studentName, String courseName);
}
