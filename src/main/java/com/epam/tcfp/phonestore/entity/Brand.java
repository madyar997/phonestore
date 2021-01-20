package com.epam.tcfp.phonestore.entity;

import java.util.Objects;

public class Brand {
    private int brandId;
    private String brandName;
    private String details;

    public Brand() {
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Brand brand = (Brand) o;
        return Objects.equals(brandName, brand.brandName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brandName);
    }
}
