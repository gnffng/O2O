package com.bong.o2o.service;

import java.util.*;

import com.bong.o2o.dao.order.*;
import com.bong.o2o.dao.product.MainMenu;
import com.bong.o2o.dao.product.Topping;
import com.bong.o2o.repository.order.OrderMenuRepository;
import com.bong.o2o.repository.order.OrderSheetRepository;
import com.bong.o2o.repository.order.OrderToppingRepository;
import com.bong.o2o.repository.product.MenuRepository;
import com.bong.o2o.repository.product.ToppingRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class OrderService {
    OrderSheetRepository orderSheetRepository;
    OrderMenuRepository orderMenuRepository;
    OrderToppingRepository orderToppingRepository;
    MenuRepository menuRepository;
    ToppingRepository toppingRepository;

    public OrderService(OrderSheetRepository orderSheetRepository,
                        OrderMenuRepository orderMenuRepository,
                        OrderToppingRepository orderToppingRepository,
                        MenuRepository menuRepository,
                        ToppingRepository toppingRepository) {
        this.orderSheetRepository = orderSheetRepository;
        this.orderMenuRepository = orderMenuRepository;
        this.orderToppingRepository = orderToppingRepository;
        this.menuRepository = menuRepository;
        this.toppingRepository = toppingRepository;
    }

    public OrderSheet createOrder(OrderForm orderForm) {
        OrderSheet orderSheet = new OrderSheet();
        orderSheet = orderSheetRepository.save(orderSheet);

        Long amount = 0L;

        List<OrderMenu> orderMenus = new ArrayList<>();
        for(OrderMenuForm orderMenuForm : orderForm.getOrderMenuForms()){
            OrderMenu orderMenu = new OrderMenu();
            orderMenu.setOrderSheet(orderSheet);
            orderMenu = orderMenuRepository.save(orderMenu);

            MainMenu mainMenu = menuRepository.findById(orderMenuForm.getIdMainMenu()).get();
            orderMenu.setMainMenu(mainMenu);

            Long price = mainMenu.getPrice();

            List<OrderTopping> orderToppings = new ArrayList<>();
            for(OrderToppingForm orderToppingForm : orderMenuForm.getOrderToppingForms()){
                OrderTopping orderTopping = new OrderTopping();
                orderTopping.setOrderMenu(orderMenu);

                Topping topping = toppingRepository.findById(orderToppingForm.getIdTopping()).get();

                orderTopping.setTopping(topping);
                orderTopping.setCount(orderToppingForm.getCount());

                orderTopping.setAmount(topping.getPrice() * orderToppingForm.getCount());
                price += orderTopping.getAmount();

                orderToppings.add(orderTopping);
                orderToppingRepository.save(orderTopping);
            }
            orderMenu.setOrderToppings(orderToppings);

            amount += price;
            orderMenu.setAmount(price);

            orderMenus.add(orderMenu);
            orderMenu = orderMenuRepository.save(orderMenu);
        }

        orderSheet.setAmount(amount);
        orderSheet.setStatus(OrderSheet.Status.Preparing);
        orderSheet.setOrderMenus(orderMenus);
        return orderSheetRepository.save(orderSheet);
    }

    public Optional<OrderSheet> readById(Long id){
        return orderSheetRepository.findById(id);
    }

    public List<OrderSheet> readAll(){
        return orderSheetRepository.findAll();
    }

    public OrderSheet update(OrderSheet orderSheet){
        return orderSheetRepository.save(orderSheet);
    }

    public void deleteOrder(Long id){
        OrderSheet orderSheet = orderSheetRepository.findById(id).get();

        for(OrderMenu orderMenu : orderMenuRepository.findByOrderSheet(orderSheet)){
            for(OrderTopping orderTopping : orderToppingRepository.findByOrderMenu(orderMenu)){
                orderToppingRepository.delete(orderTopping);
            }
            orderMenuRepository.delete(orderMenu);
        }

        orderSheetRepository.delete(orderSheet);
    }
}
