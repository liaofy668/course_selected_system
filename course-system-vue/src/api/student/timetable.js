import * as ajax from "../../common/ajax";

export const get = (username) => ajax.get("/student/timetable",{username:username});
