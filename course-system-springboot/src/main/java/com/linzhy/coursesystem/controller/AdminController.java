package com.linzhy.coursesystem.controller;

import com.linzhy.coursesystem.model.entity.Course;
import com.linzhy.coursesystem.model.entity.Student;
import com.linzhy.coursesystem.model.entity.Teacher;
import com.linzhy.coursesystem.service.CourseService;
import com.linzhy.coursesystem.service.StudentService;
import com.linzhy.coursesystem.service.TeacherService;
import com.linzhy.coursesystem.utils.Result;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class AdminController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    private static final Integer PAGE_SIZE = 20;

    /* ===================================Teacher=================================== */
    @RequestMapping(value = "/admin/teacher/page/count", method = RequestMethod.GET)
    public Map<String, Object> getTeacherPageCount(
            @RequestParam(name = "departmentName") String departmentName,
            @RequestParam(name = "name") String name) {
        Integer count = teacherService.getCount(name, departmentName);
        Map<String, Object> ret = new HashMap<>();
//        Objects.requireNonNullElse(count, 0);
        if(count == null){
            ret.put("count", 0);
        }
        else{
            ret.put("count", count);
        }

        return ret;
    }

    @RequestMapping(value = "/admin/teacher/page/{index}", method = RequestMethod.GET)
    public List<Teacher> getTeacherPage(
            @PathVariable(name = "index")Integer index,
            @RequestParam(name = "departmentName")String departmentName,
            @RequestParam(name = "name")String name
    ){
        Integer offset = (index-1)*PAGE_SIZE;
        return teacherService.paginationQuery(offset,  name,  departmentName);
    }

    @RequestMapping(value = "/admin/teacher", method = RequestMethod.POST)
    public Map<String, Object> createTeacher(
            @RequestBody Teacher teacher
    ){
        teacherService.insertTeacher(teacher);
        Map<String, Object> ret = new HashMap<>();
        ret.put("message", "Teacher added successfully");
        return ret;
    }

    @RequestMapping(value = "/admin/teacher/{id}", method = RequestMethod.GET)
    public Teacher getTeacherInfo(
            @PathVariable(value = "id")Integer teacherId
    ){
        return teacherService.getTeacherById(teacherId);
    }

    @RequestMapping(value = "/admin/teacher", method = RequestMethod.PUT)
    public Map<String, Object> updateTeacher(@RequestBody Teacher teacher){
        Integer res = teacherService.updateTeacher(teacher);
        return Result.message(res == 0?"Update teacher failed":"Teacher update successfully");
    }

    @RequestMapping(value = "/admin/teacher/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteTeacher(
            @PathVariable(value = "id")Integer teacherId
    ){
        Map<String, Object> ret = new HashMap<>();
        try{
            teacherService.deleteTeacherById(teacherId);
            ret.put("message", "Teacher delete successfully");
        }catch (Exception e){
            System.out.println(e);
            ret.put("message", "Delete teacher failed");
        }
        return ret;
    }


    /* ===================================Student=================================== */
    @RequestMapping(value = "/admin/student/page/count", method = RequestMethod.GET)
    public Map<String, Object> getStudentPageCount(
            @RequestParam(value = "className")String className,
            @RequestParam(value = "name")String studentName
    ){
        Map<String, Object> ret = new HashMap<>();
        Integer count = studentService.getPageCount(studentName, className, PAGE_SIZE);
        ret.put("count", count);
        return ret;
    }

    @RequestMapping(value = "/admin/student/page/{index}", method = RequestMethod.GET)
    public List<Student> getStudentPage(
            @PathVariable(value = "index")Integer index,
            @RequestParam(value = "className")String className,
            @RequestParam(value = "name")String studentName
    ){
        Integer offset = (index-1)*PAGE_SIZE;
        return studentService.paginationQuery(offset, studentName, className);
    }

    @RequestMapping(value = "/admin/student", method = RequestMethod.POST)
    public Map<String, Object> createStudent(
            @RequestBody Student student
    ){
        Map<String, Object> ret = new HashMap<>();
        Integer res = studentService.insertStudent(student);
        if (res == 0){
            ret.put("message", "failed to insert student");
        }else{
            ret.put("message", "student added successfully");
        }
        return ret;
    }

    @RequestMapping(value = "/admin/student/{id}", method = RequestMethod.GET)
    public Student getStudentInfo(
            @PathVariable(value = "id")Integer studentId
    ){
        return studentService.getStudentById(studentId);
    }

    @RequestMapping(value = "/admin/student", method = RequestMethod.PUT)
    public Map<String, Object> updateStudent(
            @RequestBody Student student
    ){
        Map<String, Object> ret = new HashMap<>();
        Integer res = studentService.updateStudent(student);
        if (res == 0){
            ret.put("message", "failed to update student(maybe not change)");
        }else{
            ret.put("message", "student updated successfully");
        }
        return ret;
    }

    @RequestMapping(value = "/admin/student/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteStudent(
            @PathVariable(name = "id")Integer studentId
    ){
        Integer res = studentService.deleteStudent(studentId);
        if (res == 0){
            return Result.message("failed to delete student");
        }else{
            return Result.message("student deleted successfully");
        }
    }

    /* ===================================Course=================================== */
    @RequestMapping(value = "/admin/course/page/count", method = RequestMethod.GET)
    public Map<String, Object> getCoursePageCount(
            @RequestParam(name = "name")String courseName,
            @RequestParam(name = "teacherName")String teacherName
    ){
        Integer count = courseService.getCourseCount(courseName, teacherName);
        return Result.count(count/PAGE_SIZE);
    }

    @RequestMapping(value = "/admin/course/page/{index}", method = RequestMethod.GET)
    public List<Course> getPageCourse(
            @PathVariable(value = "index")Integer index,
            @RequestParam(value = "name")String courseName,
            @RequestParam(value = "teacherName")String teacherName
    ){
        Integer offset = (index-1)*PAGE_SIZE;
        return courseService.paginationQuery(PAGE_SIZE, offset, courseName, teacherName);
    }

    @RequestMapping(value = "/admin/course/{id}", method = RequestMethod.GET)
    public Course getCourseInfo(
            @PathVariable(value = "id")Integer courseId
    ){
        return courseService.getCourseById(courseId);
    }

    @RequestMapping(value = "/admin/course/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> deleteCourse(
            @PathVariable(value = "id")Integer courseId
    ){
        Integer res = courseService.deleteCourseById(courseId);
        return Result.message(res==0?"failed to delete course":"successfully delete course");
    }


}

