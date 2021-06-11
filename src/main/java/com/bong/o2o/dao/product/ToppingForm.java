package com.bong.o2o.dao.product;

public class ToppingForm {
    String nameKor;
    String nameEn;
    Long price;

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

    Topping.Category category;
}
