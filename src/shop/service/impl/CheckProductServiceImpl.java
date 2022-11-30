package shop.service.impl;

import shop.dao.DbHelper;
import shop.dao.impl.DbHelperImpl;
import shop.exceptions.SqlException;
import shop.models.CheckProduct;
import shop.service.CheckService;
import shop.service.*;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CheckProductServiceImpl implements CheckProductService {
    DbHelper dbHelper = new DbHelperImpl();
    CheckService checkService = new CheckServiceImpl();
    ProductService productService = new ProductServiceImpl();
    @Override
    public void save(CheckProduct checkProduct) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("insert into tb_checks_product (id_tb_checks, id_tb_product,count) values (?,?,?)")){
            preparedStatement.setLong(1, checkProduct.getCheck());
            preparedStatement.setLong(2, checkProduct.getProduct());
            preparedStatement.setDouble(3, checkProduct.getCount());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(CheckProduct updatedCheckProduct) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("update tb_checks_product set id_tb_checks = ?, id_tb_product = ?, count = ? where id = ?")){
            preparedStatement.setLong(1, updatedCheckProduct.getCheck());
            preparedStatement.setLong(2, updatedCheckProduct.getProduct());
            preparedStatement.setDouble(3, updatedCheckProduct.getCount());
            preparedStatement.setLong(4, updatedCheckProduct.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new SqlException("Произошла ошибка при обновлении чек/продукт");
        }
    }

    @Override
    public List<CheckProduct> findAll() {
        try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_checks_product")){

            List<CheckProduct> checkProducts = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                CheckProduct checkProduct = new CheckProduct();
                checkProduct.setId(rs.getLong("id"));
                checkProduct.setCheck(rs.getLong("id_tb_checks"));
                checkProduct.setProduct(rs.getLong("id_tb_product"));
                checkProduct.setCount(rs.getDouble("count"));
                checkProducts.add(checkProduct);
            }
            return checkProducts;
        } catch (SQLException throwables) {
            throw new SqlException("Произошла ошибка при выводе списка чек/продукт");
        }
    }

    @Override
    public CheckProduct findById(Long id) {
            try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                    ("select * from tb_checks_product where id = ?")){
                preparedStatement.setLong(1, id);
                CheckProduct checkProduct = new CheckProduct();
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    checkProduct.setId(rs.getLong("id"));
                    checkProduct.setProduct(rs.getLong("id_tb_product"));
                    checkProduct.setCheck(rs.getLong("id_tb_checks"));
                } return checkProduct;
        } catch (SQLException throwables){
                throw new SqlException("Произошла ошибка при выводе чек/продукт");
            }
    }


    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("delete from tb_checks_product where id = ? ")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при удалении чек/продукт");
        }

    }
}
