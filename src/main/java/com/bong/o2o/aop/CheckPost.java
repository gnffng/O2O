package com.bong.o2o.aop;

import com.bong.o2o.dao.product.MainMenuForm;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class CheckPost {

    @Before("execution(* com.bong.o2o.controller..*(com.bong.o2o.dao.product.MainMenuForm))&& args(mainMenuForm)")
    public void logBeforeWithParam(MainMenuForm mainMenuForm){
        System.out.println(mainMenuForm.getNameKor());
        System.out.println(mainMenuForm.getNameEn());
        System.out.println(mainMenuForm.getPrice());
        System.out.println(mainMenuForm.getCategory());
        System.out.println(mainMenuForm.getMaterial());
    }
}
