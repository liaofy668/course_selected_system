package com.linzhy.coursesystem.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {
    private int adminId;
    private String adminName;
    private String adminUsername;
    private String adminPassword;
}
