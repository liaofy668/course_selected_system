package com.linzhy.coursesystem.controller;

import com.linzhy.coursesystem.model.entity.Course;
import com.linzhy.coursesystem.model.entity.Teacher;
import com.linzhy.coursesystem.model.entity.StudentCourse;
import com.linzhy.coursesystem.service.TeacherService;
import com.linzhy.coursesystem.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.util.List;
import java.util.Map;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    private final static Integer PAGE_SIZE=20;

    @RequestMapping(value = "teacher/timetable/{id}", method = RequestMethod.GET)
    public List<Course> getTeacherCourse(
            @PathVariable(value = "id")Integer teacherId
    ){
       return teacherService.getTeacherCourseById(teacherId);
    }

    @RequestMapping(value = "/teacher/grade/page/count", method = RequestMethod.GET)
    public Map<String, Object> getTeacherPageCount(
            @RequestParam(name = "userId")Integer teacherId,
            @RequestParam(name = "courseName")String courseName,
            @RequestParam(name = "studentName")String studentName
    ){
        Integer count = teacherService.getPageCount(teacherId, courseName, studentName);
        return Result.count(count/PAGE_SIZE);
    }

    @RequestMapping(value = "/teacher/grade/page/{index}", method = RequestMethod.GET)
    public List<StudentCourse> getTeacherPage(
            @PathVariable(name = "index")Integer index,
            @RequestParam(name = "userId")Integer teacherId,
            @RequestParam(name = "courseName")String courseName,
            @RequestParam(name = "studentName")String studentName
    ){
        return teacherService.getStudentCoursePage(index, PAGE_SIZE, teacherId, courseName, studentName);
    }

    @RequestMapping(value = "/teacher/grade/{id}", method = RequestMethod.GET)
    public StudentCourse getStudentCourseInfo(
            @PathVariable(name = "id")Integer scId
    ){
        return teacherService.getStudentCourseById(scId);
    }

    @RequestMapping(value = "/teacher/grade", method = RequestMethod.PUT)
    public Map<String, Object> updateTeacher(
            @RequestBody StudentCourse updateStudentCourse
    ){
        Integer scId = updateStudentCourse.getScId();
        Integer score = updateStudentCourse.getScScore();
        Integer res = teacherService.updateScore(scId, score);
        return Result.message(res==0?"failed to update score":"updated successfully");
    }

    @RequestMapping(value = "/teacher/info/{id}", method = RequestMethod.GET)
    public Teacher getTeacherInfo(
            @PathVariable(name = "id")Integer teacherId
    ){
        return teacherService.getTeacherById(teacherId);
    }

    @RequestMapping(value = "/teacher/info", method = RequestMethod.PUT)
    public Map<String, Object> updateTeacherInfo(
            @RequestBody Teacher teacher
    ){
        Integer res = teacherService.updateTeacher(teacher);
        return Result.message(res == 0?"Update teacher failed":"Teacher update successfully");
    }

    @RequestMapping(value = "/teacher/course/list", method = RequestMethod.GET)
    public List<Course> getCourseList(
            @RequestParam(value = "teacherName", required = false)String teacherName
    ){
        return teacherService.getCourseListByTeacherName(teacherName);
    }

    @RequestMapping(value = "/teacher/course", method = RequestMethod.POST)
    public Map<String, Object> createCourse(
            @RequestBody Course new_course
    ){
        List<Course> courseList = teacherService.getCourseListByTeacherName(LoginController.curUser.getUserName());
        for(Course course: courseList){
            if(course.getCourseDay() == new_course.getCourseDay() && course.getCourseSession() == new_course.getCourseSession()){
                return Result.message("insert course failed(duplicate date)");
            }
        }
        Integer res = teacherService.insertCourse(new_course);
        return Result.message(res == 0?"insert course failed":"Insert course successfully");
    }
}
