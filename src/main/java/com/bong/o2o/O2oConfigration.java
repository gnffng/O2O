package com.bong.o2o;

import com.bong.o2o.aop.CheckPost;
import com.bong.o2o.repository.admin.AdminRepository;
import com.bong.o2o.repository.product.MenuRepository;
import com.bong.o2o.repository.product.ToppingRepository;
import com.bong.o2o.repository.store.StoreRepository;
import com.bong.o2o.service.AdminService;
import com.bong.o2o.service.OrderService;
import com.bong.o2o.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class O2oConfigration {
    private final MenuRepository menuRepository;
    private final ToppingRepository toppingRepository;
    private final AdminRepository adminRepository;
    private final StoreRepository storeRepository;

    //INIT
    @Autowired
    public O2oConfigration( MenuRepository menuRepository,
                            ToppingRepository toppingRepository,
                            AdminRepository adminRepository,
                            StoreRepository storeRepository) {
        this.menuRepository = menuRepository;
        this.toppingRepository = toppingRepository;
        this.adminRepository = adminRepository;
        this.storeRepository = storeRepository;
    }

    //SERVICE
    @Bean
    public OrderService orderService(){
        return new OrderService(menuRepository, toppingRepository);
    }

    @Bean
    public AdminService adminService() { return new AdminService(adminRepository); }

    @Bean
    public StoreService storeService() { return new StoreService(storeRepository); }

    //AOP
    @Bean
    public CheckPost checkPost() { return new CheckPost(); }


    //FILTER
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {return new HiddenHttpMethodFilter();}
}
