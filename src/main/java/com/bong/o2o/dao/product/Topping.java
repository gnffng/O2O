package com.bong.o2o.dao.product;

import javax.persistence.Entity;

@Entity
public class Topping extends Product{
    enum Category{
        Main, Sub;
    }

    Topping.Category category;

    public Topping.Category getCategory() {
        return category;
    }

    public void setCategory(Topping.Category category) {
        this.category = category;
    }
}
