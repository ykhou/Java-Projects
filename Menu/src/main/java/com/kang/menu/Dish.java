package com.kang.menu;

public class Dish implements Cloneable {
    // 菜的编号
    private String dishNum;
    // 菜名
    private String dishName;
    // 菜的价格
    private int price;

    public Dish(String dishNum, String dishName, int price) {
        this.dishNum = dishNum;
        this.dishName = dishName;
        this.price = price;
    }

    public String  getDishNum() {
        return dishNum;
    }

    public void setDishNum(String dishNum) {
        this.dishNum = dishNum;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
