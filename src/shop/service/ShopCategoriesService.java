package shop.service;

import shop.models.ShopCategories;

import java.util.List;

public interface ShopCategoriesService {
    void save(ShopCategories shopCategories);
    void update(ShopCategories updatedShopCategory);
    List<ShopCategories> findAll();
    ShopCategories findById(Long id);
    void delete(Long id);
}
