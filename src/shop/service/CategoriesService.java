package shop.service;

import shop.models.Categories;

import java.util.List;

public interface CategoriesService {
    void save(String name);
    void update(Categories categories);
    List<Categories> findAll();
    Categories findById(Long id);
    void delete(Long id);}
