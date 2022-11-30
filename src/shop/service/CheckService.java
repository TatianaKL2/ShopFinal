package shop.service;
import shop.models.Check;

import java.util.List;

public interface CheckService {
    void save(double total_sum);
    void update(Check updatedCheck);
    List<Check> findAll();
    Check findById(Long id);
    void delete(Long id);
}
