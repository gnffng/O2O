package com.bong.o2o.dao.store;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Store {
    @Id
    long id;

    String name;
    LocalDateTime createdTimeAt;
    LocalDateTime updatedTimeAt;

    @Column(nullable = true, length = 64)
    String logoFileName;

    public Store() {
        createdTimeAt = LocalDateTime.now();
        updatedTimeAt = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoFileName() {
        return logoFileName;
    }

    public void setLogoFileName(String logoFilePath) {
        this.logoFileName = logoFilePath;
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
