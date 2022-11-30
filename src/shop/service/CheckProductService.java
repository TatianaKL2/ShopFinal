package shop.service;


import shop.models.CheckProduct;

import java.util.List;

public interface CheckProductService {
    void save(CheckProduct checkProduct);
    void update(CheckProduct updatedCheckProduct);
    List<CheckProduct> findAll();
    CheckProduct findById(Long id);
    void delete(Long id);
}
