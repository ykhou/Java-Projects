package com.kang.menu;

import java.util.ArrayList;
import java.util.List;

public class Customer {

    // 客人的属性
    // 人数
    private int count;
    // 桌号
    private int deskNum;
    // 总价
    private int totalPrice;

    // 客人点的菜
    private List<Dish> dishs = new ArrayList<>();

    public Customer(int deskNum, int count, int totalPrice) {
        this.count = count;
        this.deskNum = deskNum;
        this.totalPrice = totalPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getDeskNum() {
        return deskNum;
    }

    public void setDeskNum(int deskNum) {
        this.deskNum = deskNum;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Dish> getDishs() {
        return dishs;
    }

    public void setDishs(List<Dish> dishs) {
        this.dishs = dishs;
    }
}
