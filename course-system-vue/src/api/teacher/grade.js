import * as ajax from "../../common/ajax";

export const get = id => ajax.pureGet("/teacher/grade/" + id);

export const update = entity => ajax.put("/teacher/grade", entity);

export const getPageCount = (courseName, studentName,userId) =>
  ajax.get("/teacher/grade/page/count", {
    courseName: courseName,
    studentName: studentName,
    userId:userId
  });

export const getPage = (index, courseName, studentName,userId) =>
  ajax.get("/teacher/grade/page/" + index, {
    courseName: courseName,
    studentName: studentName,
    userId : userId
  });

export const pageSize = 20;
