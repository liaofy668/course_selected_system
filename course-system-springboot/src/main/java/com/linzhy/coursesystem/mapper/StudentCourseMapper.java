package com.linzhy.coursesystem.mapper;

import com.linzhy.coursesystem.model.entity.StudentCourse;
import com.linzhy.coursesystem.model.vo.SelectedCourse;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentCourseMapper {
    Integer selectCount(String teacherName, String courseName, String studentName);

    List<StudentCourse> paginationQuery(Integer offset, Integer pageSize, String teacherName, String courseName, String studentName);

    StudentCourse selectById(Integer scId);

    Integer updateScoreById(Integer scId, Integer score);

    StudentCourse selectByStudentNameAndCourseName(String studentName, String courseName);

    StudentCourse selectByStudentNameAndCourseId(String studentName, Integer courseId);

    Integer insert(String studentName, String courseName, String courseTeacherName);

    List<SelectedCourse> selectByStudentName(String studentName);

    Integer deleteByStudentNameAndCourseName(String studentName, String courseName);

    Integer deleteByStudentNameAndCourseId(String studentName, Integer courseId);

}
