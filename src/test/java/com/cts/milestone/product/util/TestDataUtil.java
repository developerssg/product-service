package com.cts.milestone.product.util;

import com.cts.milestone.product.entity.ProductEntity;
import com.cts.milestone.product.model.Product;
import com.cts.milestone.product.model.ProductUpdate;

import java.math.BigDecimal;

public  final class TestDataUtil {

    private TestDataUtil(){
    }

    public static Product createProduct(){
        Product product= new Product();
        product.setProductId("PRODUCT774AAB4144A32B98111C06B354");
        product.setProductName("Product");
        product.setPrice(BigDecimal.TEN);
        return product;
    }

    public static ProductEntity createProductEntity(){
        ProductEntity productEntity= new ProductEntity();
        productEntity.setProductId("PRODUCT774AAB4144A32B98111C06B354");
        productEntity.setProductName("Product");
        productEntity.setPrice(BigDecimal.TEN);
        return productEntity;
    }
    public static ProductUpdate createProductUpdate(){
        ProductUpdate productUpdate= new ProductUpdate();
        productUpdate.setProductName("Product");
        productUpdate.setPrice(BigDecimal.TEN);
        return productUpdate;
    }
}
