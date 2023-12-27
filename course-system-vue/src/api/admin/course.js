import * as ajax from "../../common/ajax";

export const get = id => ajax.get("/admin/course/" + id);

export const deleteItem = id => ajax.pureDelete("/admin/course/" + id);

export const getPageCount = (teacherName, name) =>
  ajax.get("/admin/course/page/count", {
    teacherName: teacherName,
    name: name
  });

export const getPage = (index, teacherName, name) =>
  ajax.get("/admin/course/page/" + index, {
    teacherName: teacherName,
    name: name
  });

export const listName = () => ajax.pureGet("/admin/course/names");

export const pageSize = 20;
