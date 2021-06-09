package com.bong.o2o.repository;

import com.bong.o2o.dao.main.Salady;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Salady,Long> {
}
