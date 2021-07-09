package com.bong.o2o.repository.order;

import com.bong.o2o.dao.order.OrderMenu;
import com.bong.o2o.dao.order.OrderTopping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringOrderToppingRepository extends JpaRepository<OrderTopping,Long>, OrderToppingRepository{
    @Override
    OrderTopping save(OrderTopping orderTopping);

    @Override
    Optional<OrderTopping> findById(Long id);

    @Override
    List<OrderTopping> findByOrderMenu(OrderMenu orderMenu);

    @Override
    List<OrderTopping> findAll();

    @Override
    void delete(OrderTopping orderTopping);
}
