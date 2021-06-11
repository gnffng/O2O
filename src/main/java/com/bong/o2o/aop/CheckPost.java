package com.bong.o2o.aop;

import com.bong.o2o.dao.product.MainMenu;

//@Aspect
//@Component
public class CheckPost {

    //@Before("execution(* com.bong.o2o.controller*.addMenu(MainMenu)) && args(mainMenu)")
    public void logBeforeWithParam(MainMenu mainMenu){
        System.out.println(mainMenu.getId());
        System.out.println(mainMenu.getNameKor());
        System.out.println(mainMenu.getNameEn());
        System.out.println(mainMenu.getPrice());
        System.out.println(mainMenu.getCategory());
        System.out.println(mainMenu.getMaterial());
    }
}
