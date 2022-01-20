package com.bong.o2o.dao.product;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MainMenu extends Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    public enum Category{
        Salady, WarmBowl, Sand, Wrap, WarmWrap;
    }

    Category category;
    String material;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

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
