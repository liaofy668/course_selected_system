package com.linzhy.coursesystem.mapper;


import com.linzhy.coursesystem.model.entity.Student;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    public List<Student> listStudents();


    public Student login(String userName, String password);

    Integer getCount(String studentName, String className);

    List<Student> paginationQuery(Integer offset, String studentName, String className);

    Integer insertStudent(Student new_student);

    Student getById(Integer studentId);

    Integer updateById(Student new_student);

    Integer deleteById(Integer studentId);
}
