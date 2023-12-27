package com.linzhy.coursesystem.model.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_WRITE)
//    @JsonAlias("id")
    private int teacherId;

    @JsonProperty("name")
    @JsonAlias("name")
    private String teacherName;

    @JsonProperty("username")
    @JsonAlias("username")
    private String teacherUsername;

    @JsonProperty("password")
    @JsonAlias("password")
    private String teacherPassword;

    @JsonProperty("departmentName")
    @JsonAlias("departmentName")
    private String teacherDepartment;

    @JsonProperty("sex")
    @JsonAlias("sex")
    private int teacherSex;
}
