package com.bong.o2o.repository.order;

import com.bong.o2o.dao.order.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringOrderSheetRepository extends JpaRepository<OrderSheet,Long>, OrderSheetRepository{
    @Override
    OrderSheet save(OrderSheet orderSheet);

    @Override
    Optional<OrderSheet> findById(Long id);

    @Override
    List<OrderSheet> findAll();

    @Override
    void delete(OrderSheet orderSheet);
}
