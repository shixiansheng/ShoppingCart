package com.example.shoppingcart;

import java.util.List;

/**
 * Created by J on 2017/10/25.
 */

public class ParntBean {

    public List<ChildBean> childList;
    public boolean isParntChecked;
    public String shopname;

    public ParntBean(List<ChildBean> childList, boolean isParntChecked, String shopname) {
        this.childList = childList;
        this.isParntChecked = isParntChecked;
        this.shopname = shopname;
    }

    public ParntBean() {
    }
}
