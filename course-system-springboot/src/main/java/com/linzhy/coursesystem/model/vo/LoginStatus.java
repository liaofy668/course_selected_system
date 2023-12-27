package com.linzhy.coursesystem.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class LoginStatus {
    private String message;
    private Boolean loggedIn;
    private String swnumber;
    private String password;
    private Integer userType;
    private Integer userId;
    private String userName;
}
