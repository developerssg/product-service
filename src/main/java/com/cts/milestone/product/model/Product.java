package com.cts.milestone.product.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel("Class representing a product tracked by the application.")
public class Product {
    @ApiModelProperty(notes = "This is Product UniqueId")
    private String productId;
    @ApiModelProperty(notes = "This is Product Name")
    private String productName;
    @ApiModelProperty(notes = "This is Product Price")
    private BigDecimal price;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
