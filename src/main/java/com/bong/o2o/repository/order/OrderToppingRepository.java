package com.bong.o2o.repository.order;

import com.bong.o2o.dao.order.OrderMenu;
import com.bong.o2o.dao.order.OrderSheet;
import com.bong.o2o.dao.order.OrderTopping;

import java.util.List;
import java.util.Optional;

public interface OrderToppingRepository {
    OrderTopping save(OrderTopping orderTopping);
    Optional<OrderTopping> findById(Long id);
    List<OrderTopping> findByOrderMenu(OrderMenu orderMenu);
    List<OrderTopping> findAll();
    void delete(OrderTopping orderTopping);
}
