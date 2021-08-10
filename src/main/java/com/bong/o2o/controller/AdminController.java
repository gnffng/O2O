package com.bong.o2o.controller;

import com.bong.o2o.dao.admin.Admin;
import com.bong.o2o.dao.order.OrderSheet;
import com.bong.o2o.dao.product.MainMenu;
import com.bong.o2o.dao.product.MainMenuForm;
import com.bong.o2o.dao.product.Topping;
import com.bong.o2o.dao.product.ToppingForm;
import com.bong.o2o.dao.store.Store;
import com.bong.o2o.service.AdminService;
import com.bong.o2o.service.OrderService;
import com.bong.o2o.service.ProductService;
import com.bong.o2o.service.StoreService;
import com.bong.o2o.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    ProductService productService;

    //로그인
    @GetMapping("/login")
    public String login(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken){
            return "admin/login";
        }

        return "redirect:/";
    }

//    @PostMapping("/login")
//    public String login(Admin admin){
//        Boolean isSuccese = adminService.longin(admin);
//
//        if(isSuccese){
//            return "redirect:order";
//        }
//        else{
//            return "redirect:login";
//        }
//    }l

    //주문
    @GetMapping("/order")
    public String order(Model model){
        List<OrderSheet> orders = orderService.readAll();
        model.addAttribute("orders", orders);
        model.addAttribute("active", "order");

        return "admin/order";
    }

    @DeleteMapping("/order/{id}")
    public String deleteOrder(@PathVariable Long id, Model model){
        orderService.deleteOrder(id);
        model.addAttribute("active", "order");
        return "redirect:../";
    }

    @GetMapping("/order/{id}")
    public String editOrderForm(@PathVariable Long id, Model model){
        OrderSheet orderSheet = orderService.readById(id).get();
        model.addAttribute("order", orderSheet);
        model.addAttribute("active", "order");

        return "admin/editOrder";
    }

    @PutMapping("/order/{id}")
    public String putOrder(@PathVariable Long id, OrderSheet.Status status, Model model){
        OrderSheet orderSheet = orderService.readById(id).get();
        orderSheet.setStatus(status);
        orderService.update(orderSheet);
        model.addAttribute("active", "order");

        return "redirect:../";
    }

    //상품
    @GetMapping("/product")
    public String product(Model model){
        List<MainMenu> menus = productService.readMenus();
        List<Topping> toppings = productService.readToppings();

        model.addAttribute("menus", menus);
        model.addAttribute("toppings",toppings);
        model.addAttribute("active", "product");

        return "admin/product";
    }

    @GetMapping("/product/mainMenu")
    public String addMainMenuForm(){
        return "admin/addMainMenu";
    }

    @PostMapping("/product/mainMenu")
    public String addMainMenu(MainMenuForm mainMenuForm, Model model) throws IOException {
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

        productService.createMenu(menu);

        model.addAttribute("active", "product");

        return "redirect:../";
    }

    @GetMapping("/product/mainMenu/{id}")
    public String editMainMenuForm(Model model, @PathVariable Long id){
        model.addAttribute("menu", productService.readMenuById(id).get());
        model.addAttribute("active", "product");
        return "admin/editMainMenu";
    }

    @PutMapping("/product/mainMenu/{id}")
    public String editMainMenu(@PathVariable Long id, MainMenuForm mainMenuForm, Model model) throws IOException {
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

        productService.updateMenu(id, menu);
        model.addAttribute("active", "product");

        return "redirect:../../";
    }

    @DeleteMapping("/product/mainMenu/{id}")
    public String deleteMainMenu(@PathVariable Long id, Model model){
        productService.deleteMenu(productService.readMenuById(id).get());
        model.addAttribute("active", "product");

        return "redirect:../../";
    }

    @GetMapping("/product/topping/")
    public String addToppingForm(Model model){
        model.addAttribute("active", "product");
        return "admin/addTopping";
    }

    @PostMapping("/product/topping/")
    public String addTopping(ToppingForm toppingForm, Model model) throws IOException {
        String fileName = StringUtils.cleanPath(toppingForm.getImage().getOriginalFilename());
        String uploadDir = "src/main/resources/static/image/topping/";
        try{
            FileUploadUtil.saveFile(uploadDir, fileName, toppingForm.getImage());
        }
        catch (Exception e){
            fileName = "default.png";
        }

        Topping topping = new Topping();

        topping.setCategory(toppingForm.getCategory());
        topping.setNameKor(toppingForm.getNameKor());
        topping.setNameEn(toppingForm.getNameEn());
        topping.setPrice(toppingForm.getPrice());
        topping.setLogoFileName(fileName);

        productService.createTopping(topping);
        model.addAttribute("active", "product");

        return "redirect:../";
    }

    @GetMapping("/product/topping/{id}")
    public String editToppingForm(@PathVariable Long id, Model model){
        model.addAttribute("topping", productService.readToppingById(id).get());
        model.addAttribute("active", "product");
        return "admin/editTopping";
    }

    @PutMapping("/product/topping/{id}")
    public String editTopping(@PathVariable Long id, ToppingForm toppingForm, Model model) throws IOException {
        String fileName = StringUtils.cleanPath(toppingForm.getImage().getOriginalFilename());
        String uploadDir = "src/main/resources/static/image/topping/";
        try{
            FileUploadUtil.saveFile(uploadDir, fileName, toppingForm.getImage());
        }
        catch (Exception e){
            fileName = "default.png";
        }

        Topping topping = new Topping();

        topping.setCategory(toppingForm.getCategory());
        topping.setNameKor(toppingForm.getNameKor());
        topping.setNameEn(toppingForm.getNameEn());
        topping.setPrice(toppingForm.getPrice());
        topping.setLogoFileName(fileName);

        productService.updateTopping(id, topping);
        model.addAttribute("active", "product");

        return "redirect:../../";
    }

    @DeleteMapping("/product/topping/{id}")
    public String deleteTopping(@PathVariable Long id, Model model){
        productService.deleteTopping(productService.readToppingById(id).get());
        model.addAttribute("active", "product");

        return "redirect:../../";
    }

    //통계
    @GetMapping("/statistic")
    public String statistic(Model model) {
        model.addAttribute("active", "statistic");
        return "admin/statistic";
    }


    //가게
    @GetMapping("/store")
    public String store(Model model){
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "store");
        return "admin/store";
    }

    @PostMapping("/store")
    public String initProfile(Model model){
        storeService.initStore();
        model.addAttribute("active", "store");
        return "redirect:./";
    }

    @PutMapping("/store")
    public String updateProfile(
            @RequestParam("name") String name,
            @RequestParam("image") MultipartFile multipartFile,
            Model model) throws IOException {
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
        model.addAttribute("active", "store");

        return "redirect:./";
    }

}
