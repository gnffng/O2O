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

    public boolean longin(Admin adminForm){
        try {
            Admin admin = adminRepository.findById(adminForm.getId()).orElseThrow(IllegalStateException::new);

            if(admin.getPassward().equals(adminForm.getPassward())){
                return true;
            }
            else{
                return false;
            }
        }
        catch(Exception e){
            return false;
        }


    }
}
