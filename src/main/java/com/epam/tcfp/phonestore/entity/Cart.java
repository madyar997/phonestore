package com.epam.tcfp.phonestore.entity;

import com.epam.tcfp.phonestore.constants.Constants;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Phone, Integer> productList;
    int totalQuantity;
    float totalCost;
    float shipping;


    public Cart() {
        productList = new HashMap<>();
        shipping = Constants.SHIPPING_COST;
    }

    public Map<Phone, Integer> getProductList() {
        return productList;
    }

    public void setProductList(Map<Phone, Integer> productList) {
        this.productList = productList;
    }

    public float getShipping() {
        return shipping;
    }

    public void setShipping(float shipping) {
        this.shipping = shipping;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getTotalCost() {
        this.totalCost = 0;
        for (Map.Entry<Phone, Integer> phoneItem : this.productList.entrySet()) {
            float phoneCost = phoneItem.getKey().getPrice();
            int quantity = phoneItem.getValue();
            this.totalCost += phoneCost * quantity;
        }
        return this.totalCost;
    }

    public int getTotalQuantity() {
        this.totalQuantity = 0;
        for (Map.Entry<Phone, Integer> phoneItem : this.productList.entrySet()) {
            this.totalQuantity += phoneItem.getValue();
        }
        return this.totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

}
