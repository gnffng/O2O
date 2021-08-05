package com.bong.o2o.configration;

import com.bong.o2o.aop.CheckPost;
import com.bong.o2o.repository.admin.AdminRepository;
import com.bong.o2o.repository.order.OrderMenuRepository;
import com.bong.o2o.repository.order.OrderSheetRepository;
import com.bong.o2o.repository.order.OrderToppingRepository;
import com.bong.o2o.repository.product.MenuRepository;
import com.bong.o2o.repository.product.ToppingRepository;
import com.bong.o2o.repository.store.StoreRepository;
import com.bong.o2o.service.AdminService;
import com.bong.o2o.service.OrderService;
import com.bong.o2o.service.ProductService;
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
    private final OrderSheetRepository orderSheetRepository;
    private final OrderMenuRepository orderMenuRepository;
    private final OrderToppingRepository orderToppingRepository;

    //INIT
    @Autowired
    public O2oConfigration( MenuRepository menuRepository,
                            ToppingRepository toppingRepository,
                            AdminRepository adminRepository,
                            StoreRepository storeRepository,
                            OrderSheetRepository orderSheetRepository,
                            OrderMenuRepository orderMenuRepository,
                            OrderToppingRepository orderToppingRepository) {
        this.menuRepository = menuRepository;
        this.toppingRepository = toppingRepository;
        this.adminRepository = adminRepository;
        this.storeRepository = storeRepository;
        this.orderSheetRepository = orderSheetRepository;
        this.orderMenuRepository = orderMenuRepository;
        this.orderToppingRepository = orderToppingRepository;
    }

    //SERVICE
    @Bean
    public ProductService productService(){
        return new ProductService(menuRepository, toppingRepository);
    }

    @Bean
    public AdminService adminService() { return new AdminService(adminRepository); }

    @Bean
    public StoreService storeService() { return new StoreService(storeRepository); }

    @Bean
    public OrderService orderService() {
        return new OrderService(
            orderSheetRepository,
            orderMenuRepository,
            orderToppingRepository,
                menuRepository,
                toppingRepository);
    }

    //AOP
    @Bean
    public CheckPost checkPost() { return new CheckPost(); }


    //FILTER
    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {return new HiddenHttpMethodFilter();}
}
