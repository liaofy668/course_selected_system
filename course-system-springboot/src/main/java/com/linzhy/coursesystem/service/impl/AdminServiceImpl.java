package com.linzhy.coursesystem.service.impl;

import com.linzhy.coursesystem.model.entity.Admin;
import com.linzhy.coursesystem.mapper.AdminMapper;
import com.linzhy.coursesystem.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin login(String userName, String password) {
        return adminMapper.login(userName, password);
    }
}
