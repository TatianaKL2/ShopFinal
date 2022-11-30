package shop.service.impl;

import shop.dao.DbHelper;
import shop.dao.impl.DbHelperImpl;
import shop.exceptions.SqlException;
import shop.models.Categories;
import shop.service.CategoriesService;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.*;

public class CategoriesServiceImpl implements CategoriesService {
    DbHelper dbHelper = new DbHelperImpl();

    @Override
    public void save(String name) {
        try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("insert into tb_categories (name, add_date, active) values (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setBoolean(3, true);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SqlException("Произошла ошибка при добавлении категории");
        }

    }

    @Override
    public void update(Categories categories) {
        try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("update tb_categories set name = ?, active = ? where id = ?")) {
            preparedStatement.setString(1, categories.getName());
            preparedStatement.setBoolean(2, categories.isActive());
            preparedStatement.setLong(3, categories.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SqlException("Произошла ошибка при обновлении категории");
        }
    }

    @Override
    public List<Categories> findAll() {
        try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_categories")) {

            ResultSet rs = preparedStatement.executeQuery();
            List<Categories> category = new ArrayList<>();
            while (rs.next()) {
                Categories categories = new Categories();
                categories.setId(rs.getLong("id"));
                categories.setActive(rs.getBoolean("active"));
                categories.setName(rs.getString("name"));
                categories.setAddDate(rs.getDate("add_date"));
                category.add(categories);
            }
            return category;
        } catch (SQLException throwables) {
            throw new SqlException("Произошла ошибка при выводе списка категорий");
        }
    }

    @Override
    public Categories findById(Long id) {
        try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_categories where id = ?")) {
            preparedStatement.setLong(1, id);
            Categories category = new Categories();
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                category.setId(rs.getLong("id"));
                category.setName(rs.getString("name"));
                category.setActive(rs.getBoolean("active"));
                category.setAddDate(rs.getDate("add_date"));
            }
            return category;
        } catch (SQLException throwables) {
            throw new SqlException("Произошла ошибка при ввыводе категории");
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("delete from tb_categories where id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Категория по ID: " + id + " удалена");
        } catch (SQLException throwables) {
            throw new SqlException("Произошла ошибка при удалении категории");
        }
    }
}

