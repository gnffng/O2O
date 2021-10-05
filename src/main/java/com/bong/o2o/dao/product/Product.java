package com.bong.o2o.dao.product;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    LocalDateTime createdTimeAt;
    LocalDateTime updatedTimeAt;

    public Product() {
        this.createdTimeAt = LocalDateTime.now();
        this.updatedTimeAt = LocalDateTime.now();
    }

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

    public LocalDateTime getCreatedTimeAt() {
        return createdTimeAt;
    }

    public void setCreatedTimeAt(LocalDateTime createdTimeAt) {
        this.createdTimeAt = createdTimeAt;
    }

    public LocalDateTime getUpdatedTimeAt() {
        return updatedTimeAt;
    }

    public void setUpdatedTimeAt(LocalDateTime updatedTimeAt) {
        this.updatedTimeAt = updatedTimeAt;
    }
}