package edu.fdzc.service;

import edu.fdzc.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CAI
 * @time 2021/1/20
 */
@Service
public class BookService {

    @Autowired
    BookDao bookDao;

    /**
     * 结账：传入哪个用户买了哪本书
     *
     * @param userName 用户名
     * @param isbn 图书编码
     */
    @Transactional
    public void checkout(String userName, String isbn) {
        // 减库存
        bookDao.updateStock(isbn);
        // 查价格
        int price = bookDao.getPrice(isbn);
        // 减余额
        bookDao.updateBalance(userName, price);
    }
}
