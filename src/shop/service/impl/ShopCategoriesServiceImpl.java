package shop.service.impl;

import shop.dao.DbHelper;
import shop.dao.impl.DbHelperImpl;
import shop.exceptions.SqlException;
import shop.models.*;
import shop.service.*;
import shop.service.impl.*;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopCategoriesServiceImpl implements ShopCategoriesService {
    DbHelper dbHelper = new DbHelperImpl();
    ShopService shopService = new ShopServiceImpl();
    CategoriesService categoriesService = new CategoriesServiceImpl();
    @Override
    public void save(ShopCategories shopCategories) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("insert into tb_shop_categories (id_tb_shop, id_tb_categories, active) values (?,?,?)")){
            preparedStatement.setLong(1, shopCategories.getShop());
            preparedStatement.setLong(2, shopCategories.getCategories());
            preparedStatement.setBoolean(3, true);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при сохранении магазин/категория");
        }

    }

    @Override
    public void update(ShopCategories updatedShopCategory) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("update tb_shop_categories set id_tb_shop = ?, id_tb_categories = ?, active = ? where id = ?")){
            preparedStatement.setLong(1, updatedShopCategory.getShop());
            preparedStatement.setLong(2, updatedShopCategory.getCategories());
            preparedStatement.setBoolean(3, updatedShopCategory.isActive());
            preparedStatement.setLong(4, updatedShopCategory.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при обновлении магазин/категория");
        }
    }

    @Override
    public List<ShopCategories> findAll() {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_shop_categories")){
            List<ShopCategories> shopCategories = new ArrayList<>();
            ShopCategories shopCategories1 = new ShopCategories();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                shopCategories1.setId(rs.getLong("id"));
                shopCategories1.setShop(rs.getLong("id_tb_shop"));
                shopCategories1.setCategories(rs.getLong("id_tb_categories"));
                shopCategories1.setActive(rs.getBoolean("active"));
                shopCategories.add(shopCategories1);
            } return shopCategories;
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при выводе списка магазин/категория");
        }
    }

    @Override
    public ShopCategories findById(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_shop_categories where id = ?")){
            preparedStatement.setLong(1, id);
            ShopCategories shopCategories = new ShopCategories();
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                shopCategories.setId(rs.getLong("id"));
                shopCategories.setId(shopService.findById(rs.getLong("shop_id")).getId());
                shopCategories.setId(categoriesService.findById(rs.getLong("category_id")).getId());
                shopCategories.setActive(rs.getBoolean("active"));
                preparedStatement.executeUpdate();
            } return shopCategories;
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при выводе магазин/категория");
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("delete from tb_shop_categories where id = ?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при удалении магазин/категория");
        }

    }
}
