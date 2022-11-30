package shop.models;

public class CheckProduct {
    private Long id;
    private Long check;
    private Long product;
    private double count;

    public CheckProduct() {
    }

    public CheckProduct(Long id, Long check, Long product, double count) {
        this.id = id;
        this.check = check;
        this.product = product;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCheck() {
        return check;
    }

    public void setCheck(Long check) {
        this.check = check;
    }

    public Long getProduct() {
        return product;
    }

    public void setProduct(Long product) {
        this.product = product;
    }

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "CheckProduct" + "\n===\n" +
                "ID: " + id + "\n" +
                "Check: " + check + "\n" +
                "Product: " + product + "\n" +
                "Count: " + count + "\n---------------------";
    }
}
