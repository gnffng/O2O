package com.bong.o2o.controller;

import com.bong.o2o.dao.main.Salady;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {



    @GetMapping("/")
    @ResponseBody
    public String hello(){
        Salady a = new Salady();
        a.setName_kor("테스트");
        return a.getName_kor();
    }
}
