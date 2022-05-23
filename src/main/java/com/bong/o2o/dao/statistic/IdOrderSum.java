package com.bong.o2o.dao.statistic;

import com.bong.o2o.dao.product.MainMenu;

public class IdOrderSum {
    String name;
    long y;

    public IdOrderSum(String mainMenu, long count) {
        this.name = mainMenu;
        this.y = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getY() {
        return y;
    }

    public void setY(long y) {
        this.y = y;
    }
}