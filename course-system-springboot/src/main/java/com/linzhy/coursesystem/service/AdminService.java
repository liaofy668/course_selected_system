package com.linzhy.coursesystem.service;

import com.linzhy.coursesystem.model.entity.Admin;

public interface AdminService {
    Admin login(String userName, String password);
}
