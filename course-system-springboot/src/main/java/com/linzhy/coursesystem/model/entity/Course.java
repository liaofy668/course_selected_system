package com.linzhy.coursesystem.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_WRITE)
    private int courseId;
    @JsonProperty(value = "name", access = JsonProperty.Access.READ_WRITE)
    private String courseName;
    @JsonProperty(value = "teacherName", access = JsonProperty.Access.READ_WRITE)
    private String courseTeacherName;
    @JsonProperty(value = "location", access = JsonProperty.Access.READ_WRITE)
    private String coursePlace;
    @JsonProperty(value = "day", access = JsonProperty.Access.READ_WRITE)
    private int courseDay;
    @JsonProperty(value = "session", access = JsonProperty.Access.READ_WRITE)
    private int courseSession;
}
