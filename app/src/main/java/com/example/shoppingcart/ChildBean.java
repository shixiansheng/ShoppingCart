package com.example.shoppingcart;

/**
 * Created by J on 2017/10/25.
 * 子类的bean类
 */

public class ChildBean {

    public String name;
    public double pricce;
    public int num;
    public boolean isChecked;

    public ChildBean(String name, double pricce, int num, boolean isChecked) {
        this.name = name;
        this.pricce = pricce;
        this.num = num;
        this.isChecked = isChecked;
    }

    public ChildBean(){

    }


}
