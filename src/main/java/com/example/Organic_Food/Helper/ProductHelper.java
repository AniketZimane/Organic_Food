package com.example.Organic_Food.Helper;

import org.springframework.stereotype.Component;

@Component
public class ProductHelper {
    Integer productId;
    Integer productQty;

    public ProductHelper() {
    }

    public ProductHelper(Integer productId, Integer productQty) {
        this.productId = productId;
        this.productQty = productQty;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductQty() {
        return productQty;
    }

    public void setProductQty(Integer productQty) {
        this.productQty = productQty;
    }

    @Override
    public String toString() {
        return "ProductHelper{" +
                "productId=" + productId +
                ", productQty=" + productQty +
                '}';
    }
}
