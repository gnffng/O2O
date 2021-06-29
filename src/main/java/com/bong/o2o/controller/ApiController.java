package com.bong.o2o.controller;

import com.bong.o2o.dao.product.MainMenu;
import com.bong.o2o.dao.product.MainMenuForm;
import com.bong.o2o.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ApiController {

    OrderService orderService;

    @Autowired
    public ApiController(OrderService orderService) {
        this.orderService = orderService;
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

        orderService.createMenu(newMenu);

        return newMenu;
    }

    @GetMapping("/main-menu/{id}")
    @ResponseBody
    public MainMenu findMenu(@PathVariable("nameKor") String nameKor){
        return orderService.readMenuByNameKor(nameKor)
                .orElseThrow(IllegalStateException::new);
    }

    @GetMapping("/main-menu")
    @ResponseBody
    public List<MainMenu> findMenus(){
        return orderService.readMenus();
    }

    @DeleteMapping("/main-menu/{nameKor}")
    @ResponseBody
    public MainMenu deleteMenu(@PathVariable("id") Long id){
        MainMenu mainMenu = orderService.readMenuById(id).orElseThrow(IllegalStateException::new);
        orderService.deleteMenu(mainMenu);

        return mainMenu;
    }
}
