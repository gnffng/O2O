package com.bong.o2o.dao.product;

import javax.persistence.Entity;

@Entity
public class MainMenu extends Product{
    enum Category{
        Salady, WarmBowl, Sand, Wrap, WarmWrap;
    }

    Category category;
    String material;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
