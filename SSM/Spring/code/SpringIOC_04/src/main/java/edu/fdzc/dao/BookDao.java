package edu.fdzc.dao;

import edu.fdzc.bean.Book;
import org.springframework.stereotype.Repository;

/**
 * @author CAI
 * @time 2021/1/13
 */
@Repository
public class BookDao extends BaseDao<Book> {

    @Override
    public void save() {
        System.out.println("BookDao：保存图书");
    }
}
