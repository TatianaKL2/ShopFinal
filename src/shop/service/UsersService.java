package shop.service;

import shop.dao.DbHelper;
import shop.dao.impl.DbHelperImpl;
import shop.models.Users;

import java.sql.PreparedStatement;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public interface UsersService{

    void save(Users user);
    void update(Users updatedUser);
    List <Users> findAll();
    Users findById(Long id);
    void delete(Long id);

}
