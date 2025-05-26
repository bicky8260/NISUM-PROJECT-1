package catalog.model;

public class Category {
    private int categoryID;
    private String categoryName;

    public Category() {
    }

    public Category(int categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public java.lang.String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(java.lang.String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryDesc(String categoryDesc) {
    }
// Getters and setters
}
