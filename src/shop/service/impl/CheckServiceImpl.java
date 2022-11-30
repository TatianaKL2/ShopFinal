package shop.service.impl;

import shop.dao.DbHelper;
import shop.dao.impl.DbHelperImpl;
import shop.exceptions.SqlException;
import shop.models.Check;
import shop.service.CheckService;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CheckServiceImpl implements CheckService {
    DbHelper dbHelper = new DbHelperImpl();

    @Override
    public void save(double total_sum) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("insert into tb_checks (add_date, total_sum) values (?,?)")){
            preparedStatement.setDate(1, java.sql.Date.valueOf(java.time.LocalDate.now()));
            preparedStatement.setDouble(2, total_sum);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при добавлении чека");
        }
    }

    @Override
    public void update(Check updatedCheck) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("update tb_checks set total_sum = ? where id = ?")){
            preparedStatement.setDouble(1, updatedCheck.getTotalSum());
            preparedStatement.setLong(2, updatedCheck.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при обновлении чека");
        }
    }

    @Override
    public List<Check> findAll() {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_checks")){
            List<Check> checks = new ArrayList<>();
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                Check check = new Check();
                check.setId(rs.getLong("id"));
                check.setAddDate(rs.getDate("add_date"));
                check.setTotalSum(rs.getDouble("total_sum"));
                checks.add(check);
            } return checks;
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при выводе списка чеков");
        }
    }
    @Override
    public Check findById(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("select * from tb_checks where id = ?")){
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            Check check = new Check();
            while(rs.next()){
                check.setId(rs.getLong("id"));
                check.setTotalSum(rs.getDouble("total_sum"));
            } return check;
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при выводе чека");
        }
    }

    @Override
    public void delete(Long id) {
        try(PreparedStatement preparedStatement = dbHelper.preparedStatement
                ("delete from tb_checks where id = ?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Чек по ID: " + id + " удален");
        } catch (SQLException throwables){
            throw new SqlException("Произошла ошибка при удалении чека");
        }

    }
}
