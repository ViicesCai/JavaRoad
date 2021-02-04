package edu.fdzc.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author CAI
 * @time 2021/1/20
 */
@Repository
public class BookDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 修改用户余额
     *
     * @param userName 用户名
     * @param price 金额
     */
    public void updateBalance(String userName, int price) {
        String sql = "UPDATE account SET balance = balance - ? WHERE username = ?";
        jdbcTemplate.update(sql, price, userName);
    }

    /**
     * 根据isbn获取图书价格
     *
     * @param isbn 图书编码
     * @return 图书价格
     */
    public int getPrice(String isbn) {
        String sql = "SELECT price FROM book WHERE isbn = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, isbn);
    }

    /**
     * 修改图书库存
     * @param isbn 图书编码
     */
    public void updateStock(String isbn) {
        String sql = "UPDATE book_stock SET stock = stock - 1 WHERE isbn = ?";
        jdbcTemplate.update(sql, isbn);
    }
}
