package edu.fdzc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author CAI
 * @time 2021/1/22
 */
@Service
public class MulService {
    @Autowired
    private BookService bookService;

    @Transactional
    public void mulTx() {
        bookService.checkout("Tom", "ISBN-001");
        bookService.updatePrice("ISBN-001", 998);
    }
}
