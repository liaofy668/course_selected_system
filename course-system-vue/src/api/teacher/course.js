import * as ajax from "../../common/ajax";

export const list = teacherName => ajax.get("/teacher/course/list",{teacherName:teacherName});

export const create = entity => ajax.post("/teacher/course", entity);
// 在这里，箭头（=>）用于定义一个箭头函数。箭头函数是一种简洁的函数表示方式，
// 它没有自己的this、arguments和super关键字。在JavaScript中，箭头函数的语法是使用大于号（>）表示。

