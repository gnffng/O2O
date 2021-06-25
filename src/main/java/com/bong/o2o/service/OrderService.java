package com.bong.o2o.service;

import java.util.List;
import java.util.Optional;

import com.bong.o2o.dao.product.MainMenu;
import com.bong.o2o.dao.product.Topping;
import com.bong.o2o.dao.store.Store;
import com.bong.o2o.repository.product.MenuRepository;
import com.bong.o2o.repository.product.ToppingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class OrderService {
    private final MenuRepository menuRepository;
    private final ToppingRepository toppingRepository;

    @Autowired
    public OrderService(MenuRepository menuRepository,ToppingRepository toppingRepository) {
        this.menuRepository = menuRepository;
        this.toppingRepository = toppingRepository;
    }

    //Menu Service

    ////Create
    public Long createMenu(MainMenu mainMenu) {
        validateDuplicateMenu(mainMenu);
        menuRepository.save(mainMenu);
        return mainMenu.getId();
    }

    ////Read
    public List<MainMenu> readMenus(){
        return menuRepository.findAll();
    }

    public Optional<MainMenu> readMenuByNameKor(String name){
        return menuRepository.findByNameKor(name);
    }
    public Optional<MainMenu> readMenuById(Long id){
        return menuRepository.findById(id);
    }

    ////Update
    public MainMenu updateMenu(MainMenu newMenu) {
        MainMenu menu = menuRepository.findByNameKor(newMenu.getNameKor()).orElse(new MainMenu());

        menu.setCategory(newMenu.getCategory());
        menu.setMaterial(newMenu.getMaterial());
        menu.setNameKor(newMenu.getNameKor());
        menu.setNameEn(newMenu.getNameEn());
        menu.setPrice(newMenu.getPrice());
        menu.setLogoFileName(newMenu.getLogoFileName());

        return menuRepository.save(newMenu);
    }

    ////Delete
    public void delete(MainMenu mainMenu){
        menuRepository.findById(mainMenu.getId()).ifPresent(n -> {
            menuRepository.delete(mainMenu);
        });
    }

    ////Etc
    public void validateDuplicateMenu(MainMenu mainMenu){
        menuRepository.findByNameKor(mainMenu.getNameKor()).ifPresent(n ->{
            throw new IllegalStateException("이미 있는 메뉴입니다(한글이름 중복)");
        });

        menuRepository.findByNameEn(mainMenu.getNameKor()).ifPresent(n ->{
            throw new IllegalStateException("이미 있는 메뉴입니다(영어이름 중복)");
        });
    }

    //Topping Service
    //Create
    public Long createTopping(Topping topping){
        validateDuplicateTopping(topping);
        toppingRepository.save(topping);
        return topping.getId();
    }

    //Read
    public List<Topping> readToppings(){
        return toppingRepository.findAll();
    }

    public Optional<Topping> readToppingByNameKor(String name){
        return toppingRepository.findByNameKor(name);
    }

    //Update

    //Delete
    public void delete(Topping topping){
        toppingRepository.findById(topping.getId()).ifPresent(n ->{
            toppingRepository.delete(topping);
        });
    }

    //Etc
    public void validateDuplicateTopping(Topping topping){
        toppingRepository.findByNameKor(topping.getNameKor()).ifPresent(n ->{
            throw new IllegalStateException("이미 있는 메뉴입니다(한글이름 중복)");
        });

        toppingRepository.findByNameEn(topping.getNameKor()).ifPresent(n ->{
            throw new IllegalStateException("이미 있는 메뉴입니다(영어이름 중복)");
        });
    }
}
