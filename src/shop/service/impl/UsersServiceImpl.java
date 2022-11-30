package shop.service.impl;

import shop.dao.DbHelper;
import shop.dao.impl.DbHelperImpl;
import shop.exceptions.SqlException;
import shop.models.Users;
import shop.service.ShopService;
import shop.service.UsersService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class UsersServiceImpl implements UsersService {
    DbHelper dbHelper = new DbHelperImpl();
    ShopService shopService = new ShopServiceImpl();
    Scanner scn = new Scanner(System.in);
    @Override
    public void save(Users user) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("insert into tb_users (name, login, password, active, add_date, id_tb_shop) values (?,?,?,?,?,?)")){
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setBoolean(4, true);
            preparedStatement.setDate(5, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setLong(6, user.getShop());
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Users updatedUser) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("update tb_users set name = ?, login = ?, password = ?, active = ?, id_tb_shop = ? where id = ?")){
            preparedStatement.setString(1, updatedUser.getName());
            preparedStatement.setString(2, updatedUser.getLogin());
            preparedStatement.setString(3, updatedUser.getPassword());
            preparedStatement.setBoolean(4, updatedUser.isActive());
            preparedStatement.setLong(5, updatedUser.getShop());
            preparedStatement.setLong(6, updatedUser.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при обновлении данных работника");
        }
    }

    @Override
    public List<Users> findAll() {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_users")){
            List<Users> users = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Users users1 = new Users();
                users1.setId(rs.getLong("id"));
                users1.setName(rs.getString("name"));
                users1.setLogin(rs.getString("login"));
                users1.setPassword(rs.getString("password"));
                users1.setActive(rs.getBoolean("active"));
                users1.setShop(rs.getLong("id_tb_shop"));
                users1.setAddDate(rs.getDate("add_date"));
                users.add(users1);
            } return users;
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при выводе списка работников");
        }
    }

    @Override
    public Users findById(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
               ("select * from tb_users where id = ?")){
           preparedStatement.setLong(1, id);
            Users users = new Users();
            ResultSet rs = preparedStatement.executeQuery();
           while (rs.next()){
               users.setId(rs.getLong("id"));
               users.setName(rs.getString("name"));
               users.setLogin(rs.getString("login"));
               users.setPassword(rs.getString("password"));
               users.setActive(rs.getBoolean("active"));
               users.setShop(rs.getLong("id_tb_shop"));
               users.setAddDate(rs.getDate("add_date"));
           } return users;
       } catch (SQLException e){
           throw new SqlException("Произошла ошибка при выводе работника");
       }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("delete from tb_users where id = ?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            throw new SqlException("Произошла ошибка при удалении работника");
        }

    }
}
