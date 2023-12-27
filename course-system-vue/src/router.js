import Vue from "vue";
import VueRouter from "vue-router";
import Container from "./views/Container.vue";
import Login from "./views/Login";
import StudentCourseSelect from "./views/student/StudentCourseSelect";
import StudentCourse from "./views/student/StudentCourse";
import StudentInfo from "./views/student/StudentInfo";
import StudentTimeTable from "./views/student/StudentTimeTable";
import StudentScore from "./views/student/StudentScore";

import TeacherCourse from "./views/teacher/TeacherCourse";
import TeacherTimetable from "./views/teacher/TeacherTimetable";
import TeacherGrade from "./views/teacher/TeacherGrade";
import TeacherInfo from "./views/teacher/TeacherInfo";

import AdminDepartment from "./views/admin/AdminDepartment";
import AdminClass from "./views/admin/AdminClass";
import AdminStudent from "./views/admin/AdminStudent";
import AdminTeacher from "./views/admin/AdminTeacher";
import Home from "./views/Home";
import AdminCourse from "@/views/admin/AdminCourse";

// @/ 是webpack设置的路径别名，代表什么路径，要看webpack的build文件夹下webpack.base.conf.js里面对于@是如何配置

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "container",
    component: Container,
    children: [
      // 子路由
        {
          path: "/student",
          name: "student-home",
          component: Home
        },
        {
          path: "/teacher",
          name: "teacher-home",
          component: Home
        },
        {
          path: "/admin",
          name: "admin-home",
          component: Home
        },
        {
          path: "/admin/department",
          name: "admin-department",
          component: AdminDepartment
        },
        {
          path: "/admin/class",
          name: "admin-class",
          component: AdminClass
        },
        {
          path: "/admin/student",
          name: "admin-student",
          component: AdminStudent
        },
        {
          path: "/admin/teacher",
          name: "admin-teacher",
          component: AdminTeacher
        },
        {
          path: "/admin/course",
          name: "admin-course",
          component: AdminCourse
        },
        {
          path: "/teacher/course",
          name: "teacher-course",
          component: TeacherCourse
        },
        {
          path: "/teacher/timetable",
          name: "teacher-timetable",
          component: TeacherTimetable
        },
        {
          path: "/teacher/grade",
          name: "teacher-grade",
          component: TeacherGrade
        },
        {
          path: "/teacher/info",
          name: "teacher-info",
          component: TeacherInfo
        },
        {
          path: "/student/course/select",
          name: "student-course-select",
          component: StudentCourseSelect
        },
        {
          path: "/student/course",
          name: "student-course",
          component: StudentCourse
        },
        {
          path: "/student/timetable",
          name: "student-timetable",
          component: StudentTimeTable
        },
        {
          path: "/student/score",
          name: "student-score",
          component: StudentScore
        },
        {
          path: "/student/info",
          name: "student-info",
          component: StudentInfo
        },
      ]
  },
  {
    path: "/login",
    name: "login",
    component: Login
  },

];

const router = new VueRouter({
  mode: "history",
  // mode: "history"：指定路由模式为history模式
  // 该模式下URL中不会出现#符号，而是使用浏览器的history.pushState API来进行路由跳转。
  base: process.env.BASE_URL,
  // 指定基础URL，用于构建路由的URL。它通常是你的应用程序部署的根目录，可以通过process.env.BASE_URL获取。
  routes
  // 上面定义的路由信息
  // 路由配置数组，包含了应用中的所有路由信息。
  // 每个路由对象包括了path、component等属性，用于指定路径和对应的组件
});

export default router;
