import * as ajax from "../../common/ajax";

export const get = id => ajax.pureGet("/teacher/info/"+id);

export const update = entity => ajax.put("/teacher/info", entity);
