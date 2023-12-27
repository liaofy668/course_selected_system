package com.linzhy.coursesystem.controller;

import com.linzhy.coursesystem.model.entity.Student;
import com.linzhy.coursesystem.model.entity.StudentCourse;
import com.linzhy.coursesystem.model.vo.SelectedCourse;
import com.linzhy.coursesystem.service.CourseService;
import com.linzhy.coursesystem.service.StudentService;
import com.linzhy.coursesystem.utils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    private final static Integer PAGE_SIZE = 20;

    @RequestMapping(value = "/student/info/{id}", method = RequestMethod.GET)
    public Student getStudentInfo(
            @PathVariable(name = "id") Integer studentId
    ) {
        return studentService.getStudentById(studentId);
    }

    @RequestMapping(value = "/student/info", method = RequestMethod.PUT)
    public Map<String, Object> updateStudentInfo(
            @RequestBody Student newStudent
    ) {
        Integer res = studentService.updateStudent(newStudent);
        return Result.message(res == 0 ? "failed to update" : "updated successfully");
    }

    @RequestMapping(value = "/student/course/select/page/count", method = RequestMethod.GET)
    public Map<String, Object> getCoursePageCount(
            @RequestParam(name = "courseName") String courseName,
            @RequestParam(name = "teacherName") String teacherName
    ) {
        Integer count = courseService.getCourseCount(courseName, teacherName);
        return Result.count(count / PAGE_SIZE);
    }

    @RequestMapping(value = "/student/course/select/page/{index}", method = RequestMethod.GET)
    public List<Map<String, Object>> getStudentPage(
            @PathVariable(name = "index")Integer index,
            @RequestParam(value = "studentName")String studentName,
            @RequestParam(value = "courseName")String courseName,
            @RequestParam(value = "teacherName")String teacherName
    ){
        Integer offset = (index-1)*PAGE_SIZE;
        return studentService.getCoursePage(PAGE_SIZE, offset, studentName, courseName, teacherName);
    }

    @RequestMapping(value = "/student/course/select/{id}", method = RequestMethod.POST)
    public Map<String, Object> selectCourse(
            @PathVariable(value = "id")Integer courseId,
            @RequestBody Map<String, Object> jsonData
    ){
        String studentName = (String) jsonData.get("userName");
        Integer ret = studentService.selectCourse(studentName, courseId);

        return Result.message(ret == 0?"failed to select course":"course selected successfully");
    }

    @RequestMapping(value = "/student/course/list", method = RequestMethod.GET)
    public List<SelectedCourse> getStudentSelectCourse(
            @RequestParam(value = "studentName")String studentName
    ){
        return studentService.getStudentCourse(studentName);
    }

    @RequestMapping(value = "/student/timetable", method = RequestMethod.GET)
    public List<SelectedCourse> getStudentTable(
            @RequestParam(value = "username")String studentName
    ){
        return studentService.getStudentCourse(studentName);
    }

    @RequestMapping(value = "/student/course/select/delete", method = RequestMethod.POST)
    public Map<String, Object> deleteSelectCourse(
            @RequestBody Map<String, Object> jsonData
    ){
        String studentName= (String) jsonData.get("userName");
        String courseName= (String) jsonData.get("courseName");
        Integer res = studentService.unselectCourse(studentName, courseName);
        return Result.message(res == 0?"failed to unselect course":"course unselected successfully");
    }
}
