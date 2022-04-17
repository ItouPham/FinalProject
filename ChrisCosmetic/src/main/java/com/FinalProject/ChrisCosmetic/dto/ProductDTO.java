package com.FinalProject.ChrisCosmetic.dto;

import com.FinalProject.ChrisCosmetic.entity.SubCategory;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

public class ProductDTO {

    private Long id;

    private String productName;

    private String productImage;

    private String productShortDesc;

    private String productDetailDesc;

    private Long subCategoryID;

    private int price;

    private int quantity;

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getProductShortDesc() {
        return productShortDesc;
    }

    public String getProductDetailDesc() {
        return productDetailDesc;
    }

    public Long getSubCategoryID() {
        return subCategoryID;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public void setProductShortDesc(String productShortDesc) {
        this.productShortDesc = productShortDesc;
    }

    public void setProductDetailDesc(String productDetailDesc) {
        this.productDetailDesc = productDetailDesc;
    }

    public void setSubCategoryID(Long subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDTO() {
        super();
    }

    public ProductDTO(Long id, String productName, String productImage, String productShortDesc, String productDetailDesc, Long subCategoryID, int price, int quantity, String status) {
        this.id = id;
        this.productName = productName;
        this.productImage = productImage;
        this.productShortDesc = productShortDesc;
        this.productDetailDesc = productDetailDesc;
        this.subCategoryID = subCategoryID;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productShortDesc='" + productShortDesc + '\'' +
                ", productDetailDesc='" + productDetailDesc + '\'' +
                ", subCategoryID=" + subCategoryID +
                ", price=" + price +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                '}';
    }


}
