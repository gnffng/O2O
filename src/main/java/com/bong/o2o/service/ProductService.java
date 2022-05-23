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
public class ProductService {
    private final MenuRepository menuRepository;
    private final ToppingRepository toppingRepository;

    @Autowired
    public ProductService(MenuRepository menuRepository, ToppingRepository toppingRepository) {
        this.menuRepository = menuRepository;
        this.toppingRepository = toppingRepository;
    }

    //Menu Service

    ////Create
    public MainMenu createMenu(MainMenu mainMenu) {
        validateDuplicateMenu(mainMenu);
        menuRepository.save(mainMenu);
        return mainMenu;
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
    public MainMenu updateMenu(Long id, MainMenu newMenu) {
        MainMenu menu = menuRepository.findById(id).get();

        menu.setCategory(newMenu.getCategory());
        menu.setMaterial(newMenu.getMaterial());
        menu.setNameKor(newMenu.getNameKor());
        menu.setNameEn(newMenu.getNameEn());
        menu.setPrice(newMenu.getPrice());
        menu.setLogoFileName(newMenu.getLogoFileName());

        return menuRepository.save(menu);
    }

    ////Delete
    public void deleteMenu(MainMenu mainMenu){
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
    public Optional<Topping> readToppingById(Long id){
        return toppingRepository.findById(id);
    }

    //Update
    public Topping updateTopping(Long id, Topping newMenu) {
        Topping topping = toppingRepository.findById(id).get();

        topping.setCategory(newMenu.getCategory());
        topping.setNameKor(newMenu.getNameKor());
        topping.setNameEn(newMenu.getNameEn());
        topping.setPrice(newMenu.getPrice());
        topping.setLogoFileName(newMenu.getLogoFileName());

        return toppingRepository.save(topping);
    }

    //Delete
    public void deleteTopping(Topping topping){
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
