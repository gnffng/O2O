package com.bong.o2o.dao.product;

import org.springframework.web.multipart.MultipartFile;

public class MainMenuForm {
    String nameKor;
    String nameEn;
    Long price;
    MainMenu.Category category;
    String material;
    MultipartFile image;

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getNameKor() {
        return nameKor;
    }

    public void setNameKor(String nameKor) {
        this.nameKor = nameKor;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public MainMenu.Category getCategory() {
        return category;
    }

    public void setCategory(MainMenu.Category category) {
        this.category = category;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
