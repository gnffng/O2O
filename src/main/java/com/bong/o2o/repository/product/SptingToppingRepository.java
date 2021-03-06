package com.bong.o2o.repository.product;

import com.bong.o2o.dao.product.Topping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SptingToppingRepository extends JpaRepository<Topping,Long>, ToppingRepository{
    @Override
    Optional<Topping> findByNameKor(String name);

    @Override
    Optional<Topping> findByNameEn(String name);
}
