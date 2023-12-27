package com.linzhy.coursesystem.controller;

import com.linzhy.coursesystem.model.entity.Admin;
import com.linzhy.coursesystem.model.entity.Student;
import com.linzhy.coursesystem.model.entity.Teacher;
import com.linzhy.coursesystem.model.vo.LoginStatus;
import com.linzhy.coursesystem.model.vo.LoginVO;
import com.linzhy.coursesystem.service.AdminService;
import com.linzhy.coursesystem.service.StudentService;
import com.linzhy.coursesystem.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    public static LoginStatus curUser;

    // @ResponseBody，在RestController中已有该注解，所以可以省略
    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    public LoginStatus Login(@RequestBody LoginVO params) {
        System.out.println("access /user/login");
        System.out.println(params.getUserType());
        System.out.println(params.getSwnumber());
        System.out.println(params.getPassword());

        Integer userType = params.getUserType();
        String userName = params.getSwnumber();
        String password = params.getPassword();

        curUser = new LoginStatus();
        curUser.setLoggedIn(Boolean.TRUE);
        curUser.setMessage("successfully login");
        curUser.setSwnumber(userName);
        curUser.setPassword(password);
        curUser.setUserType(userType);

        if (userType == 1) {
            Student student = studentService.login(userName, password);
            if (student == null) {
                curUser.setLoggedIn(Boolean.FALSE);
                curUser.setMessage("Invalid username");

            } else if (student.getStudentPassword().compareTo(password) != 0) {
                curUser.setLoggedIn(Boolean.FALSE);
                curUser.setMessage("Incorrect password");
            } else {
                curUser.setUserId(student.getStudentId());
                curUser.setUserName(student.getStudentUsername());
            }
        } else if (userType == 2) {
            Teacher teacher = teacherService.login(userName, password);
            if (teacher == null) {
                curUser.setLoggedIn(Boolean.FALSE);
                curUser.setMessage("Invalid username");

            } else if (teacher.getTeacherPassword().compareTo(password) != 0) {
                curUser.setLoggedIn(Boolean.FALSE);
                curUser.setMessage("Incorrect password");
            } else {
                curUser.setUserId(teacher.getTeacherId());
                curUser.setUserName(teacher.getTeacherName());
            }
        } else if (userType == 3) {
            Admin admin = adminService.login(userName, password);
            if (admin == null) {
                curUser.setLoggedIn(Boolean.FALSE);
                curUser.setMessage("Invalid username");

            } else if (admin.getAdminPassword().compareTo(password) != 0) {
                curUser.setLoggedIn(Boolean.FALSE);
                curUser.setMessage("Incorrect password");
            } else {
                curUser.setUserId(admin.getAdminId());
                curUser.setUserName(admin.getAdminUsername());
            }
        } else {
            curUser.setLoggedIn(Boolean.FALSE);
            curUser.setMessage("Invalid userType");
        }
        return curUser;
    }

    @ResponseBody
    @RequestMapping(value = "/user/login/status", method = RequestMethod.GET)
    public LoginStatus getLoginStatus() {
        if (curUser == null) {
            curUser = new LoginStatus();
            curUser.setLoggedIn(Boolean.FALSE);
        }
        return curUser;
    }

    @ResponseBody
    @RequestMapping(value = "/user/logout", method = RequestMethod.GET)
    public Map<String, Object> logout() {
        curUser = null;
        Map<String, Object> ret = new HashMap<>();
        ret.put("loggedIn", false);
        return ret;
    }
}
