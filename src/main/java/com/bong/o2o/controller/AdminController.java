package com.bong.o2o.controller;

import com.bong.o2o.dao.admin.Admin;
import com.bong.o2o.dao.product.MainMenu;
import com.bong.o2o.dao.product.MainMenuForm;
import com.bong.o2o.dao.product.Topping;
import com.bong.o2o.dao.store.Store;
import com.bong.o2o.repository.admin.AdminRepository;
import com.bong.o2o.service.AdminService;
import com.bong.o2o.service.OrderService;
import com.bong.o2o.service.StoreService;
import com.bong.o2o.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RequestMapping("/admin")
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    StoreService storeService;

    @Autowired
    OrderService orderService;

    //로그인
    @GetMapping("/login")
    public String login(){
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(Admin admin){
        Boolean isSuccese = adminService.longin(admin);

        if(isSuccese){
            return "redirect:order";
        }
        else{
            return "redirect:login";
        }
    }

    //주문
    @GetMapping("/order")
    public String main(){
        return "admin/order";
    }

    //상품
    @GetMapping("/product")
    public String product(Model model){
        List<MainMenu> menus = orderService.readMenus();
        List<Topping> toppings = orderService.readToppings();

        model.addAttribute("menus", menus);
        model.addAttribute("toppings",toppings);

        return "admin/product";
    }

    @GetMapping("/product/mainMenu")
    public String addMainMenuForm(){
        return "admin/addMainMenu";
    }

    @PostMapping("/product/mainMenu")
    public String addMainMenu(MainMenuForm mainMenuForm) throws IOException {
        String fileName = StringUtils.cleanPath(mainMenuForm.getImage().getOriginalFilename());
        String uploadDir = "src/main/resources/static/image/mainMenu/";
        FileUploadUtil.saveFile(uploadDir, fileName, mainMenuForm.getImage());

        MainMenu menu = new MainMenu();

        menu.setCategory(mainMenuForm.getCategory());
        menu.setMaterial(mainMenuForm.getMaterial());
        menu.setNameKor(mainMenuForm.getNameKor());
        menu.setNameEn(mainMenuForm.getNameEn());
        menu.setPrice(mainMenuForm.getPrice());
        menu.setLogoFileName(fileName);

        orderService.createMenu(menu);

        System.out.println("post mainMenu!");

        return "redirect:./";
    }

    @GetMapping("/product/mainMenu/{id}")
    public String editMainMenuForm(Model model, @PathVariable Long id){
        model.addAttribute("menu", orderService.readMenuById(id).get());
        return "admin/editMainMenu";
    }

    @PutMapping("/product/mainMenu/{id}")
    public String editMainMenu(MainMenuForm mainMenuForm, @PathVariable Long id) throws IOException {
        String fileName = StringUtils.cleanPath(mainMenuForm.getImage().getOriginalFilename());
        String uploadDir = "src/main/resources/static/image/mainMenu/";
        try{
            FileUploadUtil.saveFile(uploadDir, fileName, mainMenuForm.getImage());
        }
        catch (Exception e){
            fileName = "default.png";
        }

        MainMenu menu = new MainMenu();

        menu.setCategory(mainMenuForm.getCategory());
        menu.setMaterial(mainMenuForm.getMaterial());
        menu.setNameKor(mainMenuForm.getNameKor());
        menu.setNameEn(mainMenuForm.getNameEn());
        menu.setPrice(mainMenuForm.getPrice());
        menu.setLogoFileName(fileName);

        orderService.updateMenu(id, menu);

        System.out.println("put mainMenu!");

        return "redirect:../../";
    }

    //통계
    @GetMapping("/statistic")
    public String statistic(){
        return "admin/statistic";
    }


    //가게
    @GetMapping("/store")
    public String store(Model model){
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        return "admin/store";
    }

    @PostMapping("/store")
    public String initProfile(){
        storeService.initStore();
        return "redirect:./";
    }

    @PutMapping("/store")
    public String updateProfile(
            @RequestParam("name") String name,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        String uploadDir = "src/main/resources/static/image/store/";
        try{
            FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
        }
        catch (Exception e){
            fileName = "default.png";
        }

        Store store = new Store();
        store.setName(name);
        store.setLogoFileName(fileName);
        store.setUpdatedTimeAt(LocalDateTime.now());

        storeService.updateStore(store);



        return "redirect:./";
    }

}
