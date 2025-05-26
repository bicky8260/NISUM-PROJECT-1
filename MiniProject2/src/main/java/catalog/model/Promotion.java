package catalog.model;

public class Promotion {
    private String promoCode;
    private int promoTypeId;
    private String description;
    private double promoAmount;

    public Promotion(String promoCode, int promoTypeId, String description, double promoAmount) {
        this.promoCode = promoCode;
        this.promoTypeId = promoTypeId;
        this.description = description;
        this.promoAmount = promoAmount;
    }

    public Promotion() {
    }

    public int getPromoTypeId() {
        return promoTypeId;
    }

    public void setPromoTypeId(int promoTypeId) {
        this.promoTypeId = promoTypeId;
    }

    public java.lang.String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(java.lang.String promoCode) {
        this.promoCode = promoCode;
    }

    public java.lang.String getDescription() {
        return description;
    }

    public void setDescription(java.lang.String description) {
        this.description = description;
    }

    public double getPromoAmount() {
        return promoAmount;
    }

    public void setPromoAmount(double promoAmount) {
        this.promoAmount = promoAmount;
    }
// Getters and setters
}
