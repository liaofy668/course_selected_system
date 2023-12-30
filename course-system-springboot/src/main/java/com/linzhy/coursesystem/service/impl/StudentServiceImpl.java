package com.linzhy.coursesystem.service.impl;

import com.linzhy.coursesystem.mapper.CourseMapper;
import com.linzhy.coursesystem.mapper.StudentCourseMapper;
import com.linzhy.coursesystem.model.entity.Course;
import com.linzhy.coursesystem.model.entity.Student;
import com.linzhy.coursesystem.mapper.StudentMapper;
import com.linzhy.coursesystem.model.entity.StudentCourse;
import com.linzhy.coursesystem.model.vo.SelectedCourse;
import com.linzhy.coursesystem.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentMapper studentMapper;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    StudentCourseMapper studentCourseMapper;

    @Override
    public List<Student> list() {
        return studentMapper.listStudents();
    }

    @Override
    public Student login(String userName, String password) {
        return studentMapper.login(userName, password);

    }

    @Override
    public Integer getPageCount(String studentName, String className, Integer pageSize) {
        Integer count = studentMapper.getCount(studentName, className);
        return count/pageSize;
    }

    @Override
    public List<Student> paginationQuery(Integer offset, String studentName, String className) {
        return studentMapper.paginationQuery(offset, studentName, className);
    }

    @Override
    public Integer insertStudent(Student student) {
        return studentMapper.insertStudent(student);
    }

    @Override
    public Student getStudentById(Integer studentId) {
        return studentMapper.getById(studentId);
    }

    @Override
    public Integer updateStudent(Student student) {
        return studentMapper.updateById(student);
    }

    @Override
    public Integer deleteStudent(Integer studentId) {
        return studentMapper.deleteById(studentId);
    }

    @Override
    public List<Map<String, Object>> getCoursePage(Integer pageSize, Integer offset, String studentName, String courseName, String teacherName) {
        List<Course> courseList = courseMapper.paginationQuery(pageSize, offset, courseName, teacherName);
        List<Boolean> hasSelected = new ArrayList<>();

        for(Course course : courseList){
//            StudentCourse studentCourse = studentCourseMapper.selectByStudentNameAndCourseName(studentName, course.getCourseName());
            StudentCourse studentCourse = studentCourseMapper.selectByStudentNameAndCourseId(studentName, course.getCourseId());
            System.out.println(studentCourse);
            hasSelected.add(studentCourse != null);
        }

        List<Map<String, Object>> ret = new ArrayList<>();
        for(int i = 0;i<courseList.size();i++){
            Map<String, Object> course_select = new HashMap<>();
            Field[] fields = courseList.get(i).getClass().getDeclaredFields();
            try{
                for(Field field : fields){
                    field.setAccessible(true);
                    course_select.put(field.getName(), field.get(courseList.get(i)));
                }
                course_select.put("haveSelected", hasSelected.get(i));
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
            ret.add(course_select);
        }
        return ret;
    }

    @Override
    public Integer selectCourse(String studentName, Integer courseId) {
        Course course = courseMapper.getById(courseId);
        return studentCourseMapper.insert(studentName, course.getCourseName(), course.getCourseTeacherName());
    }

    @Override
    public List<SelectedCourse> getStudentCourse(String studentName) {
        return studentCourseMapper.selectByStudentName(studentName);
    }

    @Override
    public Integer unselectCourse(String studentName, String courseName) {
        StudentCourse studentCourse = studentCourseMapper.selectByStudentNameAndCourseName(studentName, courseName);
        Integer courseId = courseMapper.selectCourseIdByCourseNameAndTeacherName(studentCourse.getScCourseName(), studentCourse.getScTeacherName());
        return studentCourseMapper.deleteByStudentNameAndCourseId(studentName, courseId);
    }
}
