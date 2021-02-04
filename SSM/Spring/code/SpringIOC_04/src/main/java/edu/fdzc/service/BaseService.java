package edu.fdzc.service;

import edu.fdzc.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author CAI
 * @time 2021/1/13
 */
public class BaseService<T> {

    @Autowired
    BaseDao<T> baseDao;

    public void save() {
        baseDao.save();
    }
}
