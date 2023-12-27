package com.linzhy.coursesystem;

import com.linzhy.coursesystem.model.entity.Student;
import com.linzhy.coursesystem.mapper.StudentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CourseSystemApplicationTests {
    @Autowired
    private StudentMapper studentMapper;
    @Test
    public void testSelectStudents() {
        List<Student> students = studentMapper.listStudents();
        System.out.println(students.toString());
    }



}
