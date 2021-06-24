package com.bong.o2o.controller;

import com.bong.o2o.dao.admin.Admin;
import com.bong.o2o.dao.product.MainMenu;
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

    @GetMapping("/order")
    public String main(){
        return "admin/order";
    }

    @GetMapping("/product")
    public String product(Model model){
        List<MainMenu> menus = orderService.readMenus();
        List<Topping> toppings = orderService.readToppings();

        model.addAttribute("menus", menus);
        model.addAttribute("toppings",toppings);

        return "admin/product";
    }

    @GetMapping("/product/mainMenu")
    public String editMainMenu(){
        return "admin/editMainMenu";
    }

    @PostMapping("/product/mainMenu")
    public String editOrAddMainMenu(){
        return "redirect:product";
    }

    @GetMapping("/statistic")
    public String statistic(){
        return "admin/statistic";
    }

    @GetMapping("/store")
    public String store(Model model){
        Store store = storeService.readStore();
        model.addAttribute("store", store);
        return "admin/store";
    }

    @PostMapping("/store")
    public String initProfile(){
        storeService.initStore();
        return "redirect:store";
    }

    @PutMapping("/store")
    public String updateProfile(
            @RequestParam("name") String name,
            @RequestParam("image") MultipartFile multipartFile) throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        Store store = new Store();
        store.setName(name);
        store.setLogoFileName(fileName);
        store.setUpdatedTimeAt(LocalDateTime.now());

        storeService.updateStore(store);

        String uploadDir = "src/main/resources/static/image/store/";
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return "redirect:store";
    }

}
