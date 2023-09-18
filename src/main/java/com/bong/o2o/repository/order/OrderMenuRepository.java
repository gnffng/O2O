package com.bong.o2o.repository.order;

import com.bong.o2o.dao.order.OrderMenu;
import com.bong.o2o.dao.order.OrderSheet;
import com.bong.o2o.dao.statistic.IdOrderSum;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderMenuRepository {
    OrderMenu save(OrderMenu orderMenu);
    Optional<OrderMenu> findById(Long id);
    List<OrderMenu> findByOrderSheet(OrderSheet orderSheet);
    List<OrderMenu> findAll();
    void delete(OrderMenu orderMenu);
    @Query(value =
            "SELECT " +
                    "new com.bong.o2o.dao.statistic.IdOrderSum(om.mainMenu.nameKor, COUNT (om.id)) " +
                    "FROM OrderMenu om " +
                    "GROUP BY om.mainMenu.nameKor")
    public List<IdOrderSum> findGroupByOrderWithJPQL();
}
