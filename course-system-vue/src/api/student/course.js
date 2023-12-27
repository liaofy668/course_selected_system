import * as ajax from "../../common/ajax";

export const list = studentName => ajax.get("/student/course/list",{studentName:studentName});

export const deleteItem = studentCourseId =>
  ajax.pureDelete("/student/course/" + studentCourseId);
