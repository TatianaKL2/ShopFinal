package shop.models;

import java.util.Date;

public abstract class BaseEntity {
    private Long id;
    private String name;
    private Date addDate;
    private boolean active;

    public BaseEntity() {
    }

    public BaseEntity(Long id, String name, Date addDate, boolean active) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "\nID: " + id + "\n" +
                "Name: " + name + "\n" +
                "Date: " + addDate + "\n" +
                "Active: " + active;
    }
}
