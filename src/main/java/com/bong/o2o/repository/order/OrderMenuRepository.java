package com.bong.o2o.repository.order;

import com.bong.o2o.dao.order.OrderMenu;
import com.bong.o2o.dao.order.OrderSheet;

import java.util.List;
import java.util.Optional;

public interface OrderMenuRepository {
    OrderMenu save(OrderMenu orderMenu);
    Optional<OrderMenu> findById(Long id);
    List<OrderMenu> findByOrderSheet(OrderSheet orderSheet);
    List<OrderMenu> findAll();
    void delete(OrderMenu orderMenu);
}
