package shop.models;

public class ShopCategories {
    private Long id;
    private Long shop;
    private Long categories;
    private boolean active;

    public ShopCategories() {
    }

    public ShopCategories(Long id, Long shop, Long categories, boolean active) {
        this.id = id;
        this.shop = shop;
        this.categories = categories;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShop() {
        return shop;
    }

    public void setShop(Long shop) {
        this.shop = shop;
    }

    public Long getCategories() {
        return categories;
    }

    public void setCategories(Long categories) {
        this.categories = categories;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Shop Categories" + "\n===\n" +
                "ID: " + id + "\n" +
                "Shop: " + shop + "\n" +
                "Category: " + categories + "\n" +
                "Active: " + active + "\n---------------------";
    }
}
