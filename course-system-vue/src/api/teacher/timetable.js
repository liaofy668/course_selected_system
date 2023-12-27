import * as ajax from "../../common/ajax";

export const get = id => ajax.pureGet("/teacher/timetable/"+id);
// export const get = id => ajax.get("/admin/teacher/" + id);