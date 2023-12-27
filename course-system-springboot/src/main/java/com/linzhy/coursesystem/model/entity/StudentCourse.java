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
public class StudentCourse {
    private int scId;
    private String scStudentName;
    private String  scCourseName;
    private String scTeacherName;
    @JsonAlias(value = "score")
    private int scScore;
}
