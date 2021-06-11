package com.bong.o2o.repository.product;

import com.bong.o2o.dao.product.Topping;

import java.util.List;
import java.util.Optional;

public interface ToppingRepository {
    Topping save(Topping member);
    Optional<Topping> findById(Long id);
    Optional<Topping> findByNameKor(String name);
    Optional<Topping> findByNameEn(String name);
    Optional<Topping> findByCategory(String ctg);
    List<Topping> findAll();
    void delete(Topping topping);
}
