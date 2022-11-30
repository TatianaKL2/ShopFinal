package shop.service;

import shop.models.Shop;
import shop.models.ShopCategories;
import shop.models.Users;

import java.util.List;

public interface ShopService {
    void save(String name);
    void update(Shop updatedShop);
    List<Shop> findAll();
    Shop findById(Long id);
    void delete(Long id);
}
