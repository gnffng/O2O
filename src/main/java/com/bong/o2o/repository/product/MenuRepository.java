package com.bong.o2o.repository.product;

import com.bong.o2o.dao.product.MainMenu;

import java.util.List;
import java.util.Optional;

public interface MenuRepository {
    MainMenu save(MainMenu member);
    Optional<MainMenu> findById(Long id);
    Optional<MainMenu> findByNameKor(String name);
    Optional<MainMenu> findByNameEn(String name);
    Optional<MainMenu> findByCategory(String ctg);
    List<MainMenu> findAll();
    void delete(MainMenu mainMenu);
}
