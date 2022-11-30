package shop.service.impl;

import shop.dao.DbHelper;
import shop.dao.impl.DbHelperImpl;
import shop.exceptions.SqlException;
import shop.models.Shop;
import shop.service.ShopService;

import javax.naming.ldap.PagedResultsControl;
import javax.xml.transform.Result;
import java.sql.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ShopServiceImpl implements ShopService {
    DbHelper dbHelper = new DbHelperImpl();

    @Override
    public void save(String name) {
        try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                ( "insert into tb_shop (name, active, add_date) values (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setBoolean(2, true);
            preparedStatement.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Shop updatedShop) {
        try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("update tb_shop set name = ?, add_date = ?, active = ? where id = ?")) {
            preparedStatement.setString(1, updatedShop.getName());
            preparedStatement.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setBoolean(3, updatedShop.isActive());
            preparedStatement.setLong(4, updatedShop.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SqlException("Произошла ошибка при обновлении магазина");
        }
    }

    @Override
    public List<Shop> findAll() {
        try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_shop")) {

            List<Shop> shops = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Shop shop = new Shop();
                shop.setId(rs.getLong("id"));
                shop.setName(rs.getString("name"));
                shop.setActive(rs.getBoolean("active"));
                shop.setAddDate(rs.getDate("add_date"));
                shops.add(shop);

            } return shops;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Shop findById(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_shop where id = ?")){
            preparedStatement.setLong(1, id);
            Shop shop = new Shop();
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                shop.setId(rs.getLong("id"));
                shop.setName(rs.getString("name"));
                shop.setActive(rs.getBoolean("active"));
                shop.setAddDate(rs.getDate("add_date"));

            } return shop;
        } catch (SQLException e){
            throw new SqlException("Произоша ошибка при выводе магазина");
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("delete from tb_shop where id = ?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new SqlException("Произошла ошибка при удалении магазина");
        }

    }
}
