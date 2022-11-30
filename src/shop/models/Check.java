package shop.models;

import java.util.Date;
import java.util.List;

public class Check {
    private Long id;
    private Date addDate;
    private double totalSum;

    public Check() {
    }

    public Check(Long id, Date addDate, double totalSum) {
        this.id = id;
        this.addDate = addDate;
        this.totalSum = totalSum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(double totalSum) {
        this.totalSum = totalSum;
    }

    @Override
    public String toString() {
        return "Check" + "\n===\n" +
                "ID: " + id + "\n" +
                "Date: " + addDate + "\n" +
                "Total sum: " + totalSum + "\n---------------------";
    }
}
