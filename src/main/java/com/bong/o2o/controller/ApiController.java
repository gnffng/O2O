package com.bong.o2o.controller;

import com.bong.o2o.dao.order.OrderForm;
import com.bong.o2o.dao.order.OrderSheet;
import com.bong.o2o.dao.product.MainMenu;
import com.bong.o2o.dao.product.MainMenuForm;
import com.bong.o2o.service.NotificationService;
import com.bong.o2o.service.OrderService;
import com.bong.o2o.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ApiController {

    ProductService productService;
    OrderService orderService;
    NotificationService notificationService;

    @Autowired
    public ApiController(ProductService productService, OrderService orderService, NotificationService notificationService) {
        this.productService = productService;
        this.orderService = orderService;
        this.notificationService = notificationService;
    }

    //Order CRUD
    @GetMapping("/order/{id}")
    @ResponseBody
    public OrderSheet getOrder(@PathVariable Long id){
        return orderService.readById(id).get();
    }

    @PostMapping("/order")
    @ResponseBody
    public OrderSheet addOrder(@RequestBody OrderForm orderForm){
        OrderSheet order = orderService.createOrder(orderForm);

        ObjectMapper mapper = new ObjectMapper();
        notificationService.sendMessage(order);

        return order;
    }

    @PutMapping("/order/{id}")
    @ResponseBody
    public OrderSheet editOrder(@PathVariable Long id, @RequestBody OrderSheet newOrder){
        newOrder.setId(id);
        orderService.update(newOrder);

        return newOrder;
    }

    @DeleteMapping("/order/{id}")
    @ResponseBody
    public String deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return "delete OrderSheet(" + id + ")";
    }

}
