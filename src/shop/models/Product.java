package shop.models;

import java.util.Date;

public class Product extends BaseEntity{
    private double price;
    private int discount;
    private Long categories;

    public Product() {
    }

    public Product(Long id, String name, Date addDate, boolean active, double price, int discount, Long categories) {
        super(id, name, addDate, active);
        this.price = price;
        this.discount = discount;
        this.categories = categories;
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

    public Long getCategories() {
        return categories;
    }

    public void setCategories(Long categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return super.toString() + "Price: " + price + "\n" +
                "Discount: " + discount + "\n" +
                "Category: " + categories + "\n---------------------";
    }
}
