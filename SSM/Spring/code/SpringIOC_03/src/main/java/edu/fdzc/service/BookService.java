package edu.fdzc.service;

import edu.fdzc.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author CAI
 * @time 2021/1/12
 */
@Service
public class BookService {
    @Autowired
    private BookDao bookDao;

    public void save() {
        System.out.println("BookService 正在调用dao帮你保存图书");
        bookDao.saveBook();
    }
}
