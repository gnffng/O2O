package com.bong.o2o.repository.order;


import com.bong.o2o.dao.order.OrderSheet;

import java.util.List;
import java.util.Optional;

public interface OrderSheetRepository {
    OrderSheet save(OrderSheet orderSheet);
    Optional<OrderSheet> findById(Long id);
    List<OrderSheet> findAll();
    void delete(OrderSheet orderSheet);
}
