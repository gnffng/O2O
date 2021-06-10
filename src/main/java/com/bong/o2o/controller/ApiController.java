package com.bong.o2o.controller;

import com.bong.o2o.dao.MainMenu;
import com.bong.o2o.dao.MainMenuForm;
import com.bong.o2o.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class ApiController {

    OrderService orderService;

    @Autowired
    public ApiController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/post/mainmenu")
    @ResponseBody
    public MainMenu addMenu(MainMenuForm mainMenuForm){
        MainMenu newMenu = new MainMenu();
        newMenu.setNameKor(mainMenuForm.getNameKor());
        newMenu.setNameEn(mainMenuForm.getNameEn());
        newMenu.setPrice(mainMenuForm.getPrice());
        newMenu.setCategory(mainMenuForm.getCategory());
        newMenu.setMaterial(mainMenuForm.getMaterial());

        orderService.createMenu(newMenu);

        return newMenu;
    }
}
