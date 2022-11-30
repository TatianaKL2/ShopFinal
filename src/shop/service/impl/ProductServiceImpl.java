package shop.service.impl;

import shop.dao.DbHelper;
import shop.dao.impl.DbHelperImpl;
import shop.exceptions.SqlException;
import shop.models.Product;
import shop.service.CategoriesService;
import shop.service.ProductService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    DbHelper dbHelper = new DbHelperImpl();
    CategoriesService categoriesService = new CategoriesServiceImpl();
    @Override
    public void save(Product product) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("insert into tb_product (name, price, discount, add_date, active, id_tb_categories) values (?,?,?,?,?,?)")){
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getDiscount());
            preparedStatement.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setBoolean(5, true);
            preparedStatement.setLong(6, product.getCategories());
            preparedStatement.executeUpdate();
         } catch(SQLException throwables){
            throw new SqlException("Произошла ошибка при сохранении продукта");
         }
    }

    @Override
    public void update(Product updatedProduct) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("update tb_product set name = ?, price = ?, discount = ?, active = ? where id = ? ")){
            preparedStatement.setString(1, updatedProduct.getName());
            preparedStatement.setDouble(2, updatedProduct.getPrice());
            preparedStatement.setInt(3, updatedProduct.getDiscount());
            preparedStatement.setBoolean(4, updatedProduct.isActive());
            preparedStatement.setLong(5, updatedProduct.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при обновлении продукта");
        }
    }

    @Override
    public List<Product> findAll() {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_product")){

            List<Product> products = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getInt("discount"));
                product.setActive(rs.getBoolean("active"));
                product.setAddDate(rs.getDate("add_date"));
                product.setCategories(rs.getLong("id_tb_categories"));
                products.add(product);

            } return products;
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при выводе списка продуктов");
        }
    }

    @Override
    public Product findById(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_product where id = ?")){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Product product = new Product();
            while (rs.next()){
                product.setId(rs.getLong("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setDiscount(rs.getInt("discount"));
                product.setActive(rs.getBoolean("active"));
                product.setAddDate(rs.getDate("add_date"));
                product.setCategories(rs.getLong("id_tb_categories"));
                preparedStatement.executeUpdate();
            } return product;
        }catch (SQLException e){
            e.printStackTrace();
            throw new SqlException("Произошла ошибка при выводе продукта");
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("delete from tb_product where id = ?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new SqlException("Произошла ошибка при удалении продукта");
        }

    }
}
