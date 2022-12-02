package shop.dao.impl;

import shop.dao.DbHelper;
import shop.exceptions.SqlException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbHelperImpl implements DbHelper {
    @Override
    public PreparedStatement preparedStatement(String sql) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/shop", "postgres", "postgres");
            return connection.prepareStatement(sql);
        } catch (SQLException throwables) {
            throw new SqlException("Произошла ошибка при входе в БД");
        }
    }
}
