package shop.dao;

import java.sql.PreparedStatement;

public interface DbHelper {
    PreparedStatement preparedStatement (String sql);
}
