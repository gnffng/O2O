package com.bong.o2o;

import com.bong.o2o.aop.CheckPost;
import com.bong.o2o.repository.MenuRepository;
import com.bong.o2o.repository.ToppingRepository;
import com.bong.o2o.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class O2oConfigration {
    private final MenuRepository menuRepository;
    private final ToppingRepository toppingRepository;

    @Autowired
    public O2oConfigration(MenuRepository menuRepository, ToppingRepository toppingRepository) {
        this.menuRepository = menuRepository;
        this.toppingRepository = toppingRepository;
    }

    @Bean
    public OrderService orderService(){
        return new OrderService(menuRepository, toppingRepository);
    }

    @Bean
    public CheckPost checkPost() { return new CheckPost(); }
}
