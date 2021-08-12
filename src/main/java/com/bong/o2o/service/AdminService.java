package com.bong.o2o.service;

import com.bong.o2o.dao.admin.Admin;
import com.bong.o2o.repository.admin.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class AdminService {

    AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public void create(Admin admin){
        adminRepository.save(admin);
    }
}
