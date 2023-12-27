import * as ajax from "../../common/ajax";

export const select = (id,userName) => ajax.post("/student/course/select/" + id, {userName:userName});

export const deleteSelect = (courseName,userName) => ajax.post("/student/course/select/delete", {courseName:courseName,userName:userName});

export const getPageCount = (courseName, teacherName) =>
  ajax.get("/student/course/select/page/count", {
    courseName: courseName,
    teacherName: teacherName
  });

export const getPage = (index, courseName, teacherName, studentName) =>
  ajax.get("/student/course/select/page/" + index, {
    courseName: courseName,
    teacherName: teacherName,
    studentName: studentName
  });

export const pageSize = 20;
