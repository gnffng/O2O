package com.bong.o2o.service;

import com.bong.o2o.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    MenuRepository menuRepository;
}
