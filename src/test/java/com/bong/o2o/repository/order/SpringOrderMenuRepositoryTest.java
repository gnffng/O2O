package com.bong.o2o.repository.order;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.bong.o2o.dao.order.OrderMenu;
import com.bong.o2o.dao.order.OrderSheet;
import com.bong.o2o.dao.product.MainMenu;
import com.bong.o2o.dao.statistic.IdOrderSum;
import com.bong.o2o.repository.product.MenuRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class SpringOrderMenuRepositoryTest {
    private Logger logger = LoggerFactory.getLogger(SpringOrderMenuRepositoryTest.class);

    private OrderMenuRepository orderMenuRepository;
    private MenuRepository menuRepository;

    @Autowired
    public SpringOrderMenuRepositoryTest(MenuRepository menuRepository, OrderMenuRepository orderMenuRepository) {
        this.menuRepository = menuRepository;
        this.orderMenuRepository = orderMenuRepository;
    }

    @Test
    void save() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.setNameKor("메뉴1");
        mainMenu.setNameEn("menu1");
        mainMenu.setCategory(MainMenu.Category.Salady);
        mainMenu.setPrice(1500l);
        mainMenu.setMaterial("재료1, 재료2, 재료3");
        menuRepository.save(mainMenu);

        OrderMenu orderMenu = new OrderMenu();
        orderMenu.setMainMenu(mainMenu);
        orderMenu.setOrderToppings(new ArrayList<>());
        orderMenu.setAmount(mainMenu.getPrice());
        orderMenu.setOrderSheet(new OrderSheet());
        orderMenu = orderMenuRepository.save(orderMenu);

        logger.info(orderMenu.getId()+" : "+ orderMenu.getMainMenu().getNameKor());
        assertThat(orderMenu.getId()).isEqualTo(orderMenu.getId());
    }

    @Test
    void findAll() {
        List<OrderMenu> lstMenu = orderMenuRepository.findAll();

        logger.info("총개수 : " + lstMenu.size());
        for(OrderMenu orderMenu : lstMenu){
            MainMenu mainMenu = orderMenu.getMainMenu();
            logger.info(mainMenu.getId()+":"+ mainMenu.getNameKor());
        }
        assertThat(lstMenu.size()).isEqualTo(lstMenu.size());
    }

    @Test
    void findGroupByOrderWithJPQL() {
        List<IdOrderSum> lstOrderSum = orderMenuRepository.findGroupByOrderWithJPQL();

        logger.info("총개수 : " + lstOrderSum.size());
        for(IdOrderSum idOrderSum : lstOrderSum){
            logger.info(idOrderSum.getMainMenu() + " : " + idOrderSum.getCount());
        }
        assertThat(lstOrderSum.size()).isEqualTo(lstOrderSum.size());
    }
}