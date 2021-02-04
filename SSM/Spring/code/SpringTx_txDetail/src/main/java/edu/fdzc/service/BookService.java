package edu.fdzc.service;

import edu.fdzc.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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
     * 事务细节：
     * isolation-Isolation:事务隔离级别
     * propagation-Propagation:事务传播行为
     *      传播行为（事务的传播 + 事务的行为）
     *      如果子事务进行嵌套运行，子事务是否要和大事务共用一个事务
     *
     *
     * noRollbackFor-Class[]:哪些异常事务不回滚
     * noRollbackForClassName-String[]:String表示全类名
     *
     * rollbackFor-Class[]:哪些异常事务需要回滚
     * rollbackForClassName-String[]:String表示全类名
     *
     * 异常分类：
     *      运行时异常(非检查异常)：可以不用处理，默认回滚
     *      编译时异常(检查异常)：要么try-catch；要么throws，默认不回滚
     *
     * readOnly-boolean:设定事务是否只读：默认为false
     *      可以进行事务优化：readOnly = true：加快查询速度
     *
     * timeout-int:超时时间：超出指定执行时长后自动终止并回滚：int以秒为单位
     *
     * @param userName 用户名
     * @param isbn 图书编码
     */
    @Transactional()
    public void checkout(String userName, String isbn) {
        // 减库存
        bookDao.updateStock(isbn);
        // 查价格
        int price = bookDao.getPrice(isbn);
        // 减余额
        bookDao.updateBalance(userName, price);
    }

    /**
     * 根据业务的特性进行调整
     * 读取未提交的事务可能造成脏读
     *
     * @param isbn 图书编码
     * @return 图书价格
     */
    @Transactional(readOnly = true, isolation = Isolation.READ_UNCOMMITTED)
    public int getPrice(String isbn) {
        return bookDao.getPrice(isbn);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void updatePrice(String isbn, int price) {
        bookDao.updatePrice(isbn, price);
    }
}
