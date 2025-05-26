package catalog.model;

public class PromotionType {
    private int promoTypeId;
    private String promoType;

    public PromotionType(int promoTypeId, String promoType) {
        this.promoTypeId = promoTypeId;
        this.promoType = promoType;
    }

    // Getter and setter for promoTypeId
    public int getPromoTypeId() {
        return promoTypeId;
    }

    public void setPromoTypeId(int promoTypeId) {
        this.promoTypeId = promoTypeId;
    }

    // Getter and setter for promoType
    public String getPromoType() {
        return promoType;
    }

    public void setPromoType(String promoType) {
        this.promoType = promoType;
    }
}
