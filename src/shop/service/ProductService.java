package shop.service;

import shop.models.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    void update(Product updatedProduct);
    List<Product> findAll();
    Product findById(Long id);
    void delete(Long id);
}
