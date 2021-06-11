package com.bong.o2o.repository.admin;

import com.bong.o2o.dao.admin.Admin;
import com.bong.o2o.dao.product.MainMenu;

import java.util.List;
import java.util.Optional;

public interface AdminRepository {
    Admin save(Admin admin);
    Optional<Admin> findById(String id);
    List<Admin> findAll();
    void delete(Admin admin);
}
