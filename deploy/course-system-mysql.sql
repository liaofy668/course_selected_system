# show databases ;
drop database  if exists  database_hw;
create database if not exists database_hw;
use database_hw;


drop table if exists  student_course;
drop table if exists  course;
drop table if exists  teacher;
drop table if exists  student;
drop table if exists admin;


create table admin (
    admin_id int primary key ,
    admin_name varchar(20) ,
    admin_username varchar(20) unique,
    admin_password varchar(20)
);

create table student(
    student_id int primary key auto_increment,
    student_name varchar(20) ,
    student_username varchar(20) unique ,
    student_password varchar(20),
    student_class varchar(20),
    student_sex int
)auto_increment=100000;

create table teacher(
    teacher_id int primary key auto_increment,
    teacher_name varchar(20) ,
    teacher_username varchar(20) unique ,
    teacher_password varchar(20),
    teacher_department varchar(20),
    teacher_sex int
)auto_increment=10000;

create table course (
    course_id int primary key auto_increment,
    course_name varchar(20) ,
    course_teacher_id int ,
    course_place varchar(20),
    course_day int check (course_day between 1 and 7),
    course_session int check ( course_session between 1 and 7),
    foreign key (course_teacher_id) references teacher(teacher_id)
)auto_increment=300;

create table student_course (
    sc_id int primary key auto_increment,
    sc_student_id int,
    sc_course_id int,
#     sc_teacher_name varchar(20),
    sc_score int ,
    foreign key (sc_course_id) references course(course_id),
    foreign key (sc_student_id) references  student(student_id)
#     foreign key (sc_teacher_name) references  teacher(teacher_name)
)auto_increment=800000;

insert into admin values (100,'admin','admin',123456);
insert into teacher(teacher_name, teacher_username, teacher_password, teacher_department, teacher_sex)
    value ('mary','mary','654321','computer',0);

insert into teacher(teacher_name, teacher_username, teacher_password, teacher_department, teacher_sex)
    value ('mike','mike','654321','computer',1);


insert into student(student_name, student_username, student_password, student_class, student_sex)
    value ('amy','amy','654321','101',0);

insert into student(student_name, student_username, student_password, student_class, student_sex)
    value ('bob','bob','654321','102',1);

insert into course (course_name, course_teacher_id,course_place,course_day,course_session)
        values ('c++','10001','D402',3,3);

insert into course (course_name, course_teacher_id,course_place,course_day,course_session)
        values ('python','10000','B303',3,5);

insert into course (course_name, course_teacher_id,course_place,course_day,course_session)
        values ('java','10001','C303',1,2);

insert into student_course(sc_course_id,sc_student_id)
    values ('300','100000');

insert into student_course(sc_course_id,sc_student_id)
    values ('302','100000');

# select * from admin;
# select * from student;
# select * from teacher;
# select * from course;
# select * from student_course;
