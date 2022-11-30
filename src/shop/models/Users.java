package shop.models;

import java.util.Date;

public class Users extends BaseEntity {
    private String login;
    private String password;
    private Long shop;

    public Users() {
    }

    public Users(Long id, String name, Date addDate, boolean active, String login, String password, Long shop) {
        super(id, name, addDate, active);
        this.login = login;
        this.password = password;
        this.shop = shop;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getShop() {
        return shop;
    }


    public void setShop(Long shop) {
        this.shop = shop;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLogin: " + login + "\n" +
                "Password: " + password + "\n" +
                "Shop: " + shop + "\n---------------------";
    }
}
