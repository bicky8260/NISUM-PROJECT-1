package catalog.model;

public class ProductCategory {
    private int categoryID;
    private int productID;
    private String sku;
    private double price;
    private int discount;
    private double discountPrice;

    public ProductCategory(int categoryID, int productID, String sku, double price, int discount, double discountPrice) {
        this.categoryID = categoryID;
        this.productID = productID;
        this.sku = sku;
        this.price = price;
        this.discount = discount;
        this.discountPrice = discountPrice;
    }

    // Getters and setters

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public java.lang.String getSku() {
        return sku;
    }

    public void setSku(java.lang.String sku) {
        this.sku = sku;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
