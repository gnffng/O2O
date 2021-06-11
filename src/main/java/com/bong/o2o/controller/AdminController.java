package com.bong.o2o.controller;

import com.bong.o2o.dao.admin.Admin;
import com.bong.o2o.repository.admin.AdminRepository;
import com.bong.o2o.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/login")
    public String login(){
        return "admin/Login";
    }

    @PostMapping("/login")
    public String login(Admin admin){
        Boolean isSuccese = adminService.longin(admin);

        if(isSuccese){
            return "admin/Main";
        }
        else{
            return "admin/Login";
        }
    }

}
