package com.bong.o2o.dao.product;

import javax.persistence.*;

@MappedSuperclass
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String nameKor;

    String nameEn;
    Long price;

    @Column(nullable = true, length = 64)
    String logoFileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameKor() {
        return nameKor;
    }

    public void setNameKor(String name_kor) {
        this.nameKor = name_kor;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String name_en) {
        this.nameEn = name_en;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(String logoFileName) {
        this.logoFileName = logoFileName;
    }
}