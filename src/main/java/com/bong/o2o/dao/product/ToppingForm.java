package com.bong.o2o.dao.product;

import org.springframework.web.multipart.MultipartFile;

public class ToppingForm {
    String nameKor;
    String nameEn;
    Long price;
    Topping.Category category;
    MultipartFile image;

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

    public Topping.Category getCategory() {
        return category;
    }

    public void setCategory(Topping.Category category) {
        this.category = category;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
