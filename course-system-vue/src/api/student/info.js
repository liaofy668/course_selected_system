import * as ajax from "../../common/ajax";

export const get = id => ajax.pureGet("/student/info/"+id);

export const update = entity => ajax.put("/student/info", entity);
