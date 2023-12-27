import * as ajax from "../../common/ajax";

export const get = id => ajax.get("/admin/student/" + id);

export const create = entity => ajax.post("/admin/student", entity);

export const deleteItem = id => ajax.pureDelete("/admin/student/" + id);

export const update = entity => ajax.put("/admin/student", entity);

export const getPageCount = (className, name) =>
  ajax.get("/admin/student/page/count", {
    className: className,
    name: name
  });

export const getPage = (index, className, name) =>
  ajax.get("/admin/student/page/" + index, {
    className: className,
    name: name
  });

export const listName = () => ajax.pureGet("/admin/student/names");

export const pageSize = 20;
