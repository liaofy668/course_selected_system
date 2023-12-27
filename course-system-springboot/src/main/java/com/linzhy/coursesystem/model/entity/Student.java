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
public class Student {
    @JsonProperty(value = "id", access = JsonProperty.Access.READ_WRITE)
    private Integer studentId;
    @JsonProperty(value = "name", access = JsonProperty.Access.READ_WRITE)
    private String studentName;
    @JsonProperty(value = "username", access = JsonProperty.Access.READ_WRITE)
    private String studentUsername;
    @JsonProperty(value = "password", access = JsonProperty.Access.READ_WRITE)
    private String studentPassword;
    @JsonProperty(value = "className", access = JsonProperty.Access.READ_WRITE)
    private String studentClass;
    @JsonProperty(value = "sex", access = JsonProperty.Access.READ_WRITE)
    private Integer studentSex;
}
