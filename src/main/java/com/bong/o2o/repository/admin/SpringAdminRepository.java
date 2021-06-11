package com.bong.o2o.repository.admin;

import com.bong.o2o.dao.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringAdminRepository extends JpaRepository<Admin,Long>, AdminRepository{
}
