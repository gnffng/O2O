package com.bong.o2o.dao.product;

import javax.persistence.Entity;

@Entity
public class Topping extends Product{
    public enum Category{
        Main, Sub, Dressing;
    }

    Category category;

    public Topping() {
        super();
    }

    public Topping.Category getCategory() {
        return category;
    }

    public void setCategory(Topping.Category category) {
        this.category = category;
    }
}
