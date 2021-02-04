package edu.fdzc.dao;

import org.springframework.stereotype.Repository;

/**
 * @author CAI
 * @time 2021/1/12
 */
@Repository
public class BookDao {

    public void saveBook() {
        System.out.println("图书已保存");
    }
}
