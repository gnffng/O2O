package com.bong.o2o.repository;

import com.bong.o2o.dao.MainMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringMenuRepository extends JpaRepository<MainMenu,Long>, MenuRepository {
    @Override
    Optional<MainMenu> findByNameKor(String name);

    @Override
    Optional<MainMenu> findByNameEn(String name);
}
