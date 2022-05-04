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
    @PostMapping("/order")
    @ResponseBody
    public OrderForm addOrder(@RequestBody OrderForm orderForm) throws JsonProcessingException {
        orderService.createOrder(orderForm);

        ObjectMapper mapper = new ObjectMapper();
        notificationService.sendMessage(mapper.writeValueAsString(orderForm));

        return orderForm;
    }

    @DeleteMapping("/order/{id}")
    @ResponseBody
    public String deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return "delete OrderSheet(" + id + ")";
    }

    //MainMenu CRUD
    @PostMapping("/main-menu")
    @ResponseBody
    public MainMenu addMenu(@RequestBody MainMenuForm mainMenuForm){
        MainMenu newMenu = new MainMenu();
        newMenu.setNameKor(mainMenuForm.getNameKor());
        newMenu.setNameEn(mainMenuForm.getNameEn());
        newMenu.setPrice(mainMenuForm.getPrice());
        newMenu.setCategory(mainMenuForm.getCategory());
        newMenu.setMaterial(mainMenuForm.getMaterial());

        productService.createMenu(newMenu);

        return newMenu;
    }

    @GetMapping("/main-menu/{id}")
    @ResponseBody
    public MainMenu findMenu(@PathVariable("nameKor") String nameKor){
        return productService.readMenuByNameKor(nameKor)
                .orElseThrow(IllegalStateException::new);
    }

    @GetMapping("/main-menu")
    @ResponseBody
    public List<MainMenu> findMenus(){
        return productService.readMenus();
    }

    @DeleteMapping("/main-menu/{nameKor}")
    @ResponseBody
    public MainMenu deleteMenu(@PathVariable("id") Long id){
        MainMenu mainMenu = productService.readMenuById(id).orElseThrow(IllegalStateException::new);
        productService.deleteMenu(mainMenu);

        return mainMenu;
    }
}
