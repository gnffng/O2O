package com.bong.o2o.repository.order;

import com.bong.o2o.dao.order.OrderMenu;
import com.bong.o2o.dao.order.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringOrderMenuRepository extends JpaRepository<OrderMenu,Long>, OrderMenuRepository{
    @Override
    OrderMenu save(OrderMenu orderMenu);

    @Override
    Optional<OrderMenu> findById(Long id);

    @Override
    List<OrderMenu> findByOrderSheet(OrderSheet orderSheet);

    @Override
    List<OrderMenu> findAll();

    @Override
    void delete(OrderMenu orderMenu);
}
