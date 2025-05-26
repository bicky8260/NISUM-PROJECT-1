package catalog.model;

import java.math.BigDecimal;

public class Product {
    private int productID;              // use camelCase for fields
    private String name;
    private String sku;                 // lowercase sku for consistency
    private String categoryName;
    private String size;                // lowercase size
    private BigDecimal price;
    private int discount;               // as int (percent)
    private BigDecimal discountPrice;

    public Product() {}

    public Product(String name, String sku, String categoryName, String size, double price, int discount, double discountPrice) {
        this.name = name;
        this.sku = sku;
        this.categoryName = categoryName;
        this.size = size;
        this.price = BigDecimal.valueOf(price);
        this.discount = discount;
        this.discountPrice = BigDecimal.valueOf(discountPrice);
    }

    // Getters and setters

    public int getProductID() {
        return productID;
    }
    public void setProductID(int productID) {
        this.productID = productID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSku() {
        return sku;
    }
    public void setSku(String sku) {
        this.sku = sku;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public int getDiscount() {
        return discount;
    }
    public void setDiscount(int discount) {
        this.discount = discount;
    }
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }
    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }
}
