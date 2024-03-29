package com.bong.o2o.controller;

import com.bong.o2o.dao.admin.Admin;
import com.bong.o2o.dao.order.OrderSheet;
import com.bong.o2o.dao.product.MainMenu;
import com.bong.o2o.dao.product.MainMenuForm;
import com.bong.o2o.dao.product.Topping;
import com.bong.o2o.dao.product.ToppingForm;
import com.bong.o2o.dao.statistic.IdOrderSum;
import com.bong.o2o.dao.store.Store;
import com.bong.o2o.service.AdminService;
import com.bong.o2o.service.OrderService;
import com.bong.o2o.service.ProductService;
import com.bong.o2o.service.StoreService;
import com.bong.o2o.util.FileUploadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
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

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/test")
    public String test(Model model){
        List<IdOrderSum> lstGroupByMenuId = orderService.groubByMenuId();
        model.addAttribute("list", lstGroupByMenuId);
        return "admin/testPie";
    }

    @GetMapping("/sendNotification")
    public String sendNotification(){
        return "admin/sendNotification";
    }

    @GetMapping("/notification")
    public String notification(){
        return "admin/notification";
    }

    //테스트용 admin계정 생성
    @GetMapping("/signUp")
    public String signUp(){
        Admin admin = Admin.builder()
                .id("admin")
                .password(passwordEncoder.encode("123"))
                .role("ROLE_ADMIN")
                .build();

        adminService.create(admin);

        return "redirect:/admin/login";
    }

    //로그인
    @GetMapping("/login")
    public String login(Model model){
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "store");

        return "admin/login";
    }

    //주문
    @GetMapping("/order")
    public String order(Model model){
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        
        List<OrderSheet> orders = orderService.readAll();
        model.addAttribute("orders", orders);
        model.addAttribute("active", "order");

        return "admin/order";
    }

    @DeleteMapping("/order/{id}")
    public String deleteOrder(@PathVariable Long id, Model model){
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "order");
        
        orderService.deleteOrder(id);
        return "redirect:../";
    }

    @GetMapping("/order/{id}")
    public String editOrderForm(@PathVariable Long id, Model model){
        OrderSheet orderSheet = orderService.readById(id).get();
        model.addAttribute("orderSheet", orderSheet);

        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "order");

        return "admin/editOrder";
    }

    @PutMapping("/order/{id}")
    public String putOrder(@PathVariable Long id, OrderSheet.Status status, Model model){
        OrderSheet orderSheet = orderService.readById(id).get();
        orderSheet.setStatus(status);
        orderService.update(orderSheet);

        Store store = storeService.readStore();
        model.addAttribute("store", store);
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

        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "product");

        return "admin/product";
    }

    @GetMapping("/product/mainMenu")
    public String addMainMenuForm(Model model){
        Store store = storeService.readStore();
        model.addAttribute("store", store);

        return "admin/addMainMenu";
    }

    @PostMapping("/product/mainMenu")
    public String addMainMenu(@Value("${custom.path.upload-images}") String uploadPath,
                              MainMenuForm mainMenuForm,
                              Model model) throws IOException {
        MainMenu mainMenu = new MainMenu();

        mainMenu.setCategory(mainMenuForm.getCategory());
        mainMenu.setNameKor(mainMenuForm.getNameKor());
        mainMenu.setNameEn(mainMenuForm.getNameEn());
        mainMenu.setPrice(mainMenuForm.getPrice());
        mainMenu.setMaterial(mainMenuForm.getMaterial());

        String uploadDir = uploadPath+"/image/mainMenu";
        String fileName = mainMenu.getUpdatedTimeAt().toString().replace(".", "-").replace(":", "-")+".png";

        mainMenu.setLogoFileName(fileName);

        try{
            FileCopyUtils.copy(mainMenuForm.getImage().getBytes(), new File(uploadDir, fileName));
        }
        catch (Exception e){
            mainMenu.setLogoFileName("default.png");
            log.info(e.toString());
        }

        productService.createMenu(mainMenu);

        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "product");

        return "redirect:../";
    }

    @GetMapping("/product/mainMenu/{id}")
    public String editMainMenuForm(Model model, @PathVariable Long id){
        model.addAttribute("menu", productService.readMenuById(id).get());

        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "product");
        return "admin/editMainMenu";
    }

    @PutMapping("/product/mainMenu/{id}")
    public String editMainMenu(
            @Value("${custom.path.upload-images}") String uploadPath,
            @PathVariable Long id,
            MainMenuForm mainMenuForm,
            Model model) throws IOException {
        String fileName = StringUtils.cleanPath(mainMenuForm.getImage().getOriginalFilename());
        String uploadDir = uploadPath+"/image/mainMenu";
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

        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "product");

        return "redirect:../../";
    }

    @DeleteMapping("/product/mainMenu/{id}")
    public String deleteMainMenu(@PathVariable Long id, Model model){
        productService.deleteMenu(productService.readMenuById(id).get());
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "product");

        return "redirect:../../";
    }

    @GetMapping("/product/topping/")
    public String addToppingForm(Model model){
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "product");
        return "admin/addTopping";
    }

    @PostMapping("/product/topping/")
    public String addTopping(
            @Value("${custom.path.upload-images}") String uploadPath,
            ToppingForm toppingForm,
            Model model) throws IOException {
        Topping topping = new Topping();

        topping.setCategory(toppingForm.getCategory());
        topping.setNameKor(toppingForm.getNameKor());
        topping.setNameEn(toppingForm.getNameEn());
        topping.setPrice(toppingForm.getPrice());

        String uploadDir = uploadPath+"/image/topping";
        String fileName = topping.getUpdatedTimeAt().toString().replace(".", "-").replace(":", "-")+".png";

        topping.setLogoFileName(fileName);

        try{
            FileCopyUtils.copy(toppingForm.getImage().getBytes(), new File(uploadDir, fileName));
        }
        catch (Exception e){
            topping.setLogoFileName("default.png");
            log.info(e.toString());
        }

        productService.createTopping(topping);
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "product");

        return "redirect:../";
    }

    @GetMapping("/product/topping/{id}/")
    public String editToppingForm(@PathVariable Long id, Model model){
        model.addAttribute("topping", productService.readToppingById(id).get());
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "product");
        return "admin/editTopping";
    }

    @PutMapping("/product/topping/{id}")
    public String editTopping(
            @Value("${custom.path.upload-images}") String uploadPath,
            @PathVariable Long id,
            ToppingForm toppingForm,
            Model model) throws IOException {
        Topping topping = new Topping();

        topping.setCategory(toppingForm.getCategory());
        topping.setNameKor(toppingForm.getNameKor());
        topping.setNameEn(toppingForm.getNameEn());
        topping.setPrice(toppingForm.getPrice());

        String uploadDir = uploadPath+"/image/topping";
        String fileName = topping.getUpdatedTimeAt().toString().replace(".", "-").replace(":", "-")+".png";

        topping.setLogoFileName(fileName);

        try{
            FileCopyUtils.copy(toppingForm.getImage().getBytes(), new File(uploadDir, fileName));
        }
        catch (Exception e){
            topping.setLogoFileName("default.png");
            log.info(e.toString());
        }

        productService.updateTopping(id, topping);
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "product");

        return "redirect:../../";
    }

    @DeleteMapping("/product/topping/{id}")
    public String deleteTopping(@PathVariable Long id, Model model){
        productService.deleteTopping(productService.readToppingById(id).get());
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        model.addAttribute("active", "product");

        return "redirect:../../";
    }

    //통계
    @GetMapping("/statistic")
    public String statistic(Model model) {
        List<IdOrderSum> lstGroupByMenuId = orderService.groubByMenuId();
        model.addAttribute("list", lstGroupByMenuId);

        Store store = storeService.readStore();
        model.addAttribute("store", store);
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
            @Value("${custom.path.upload-images}") String uploadPath,
            Model model) throws IOException {

        String uploadDir = uploadPath+"/image/store";

        Store store = new Store();
        store.setName(name);
        store.setLogoFileName(store.getUpdatedTimeAt().toString() + "png");

        try{
            FileCopyUtils.copy(multipartFile.getBytes(), new File(uploadDir, store.getUpdatedTimeAt().toString().replace(".", "-").replace(":", "-")+".png"));
        }
        catch (Exception e){
            store.setLogoFileName("default.png");
            log.info(e.toString());
        }

        storeService.updateStore(store);
        model.addAttribute("active", "store");

        log.info(uploadDir);

        return "redirect:./";
    }

}
