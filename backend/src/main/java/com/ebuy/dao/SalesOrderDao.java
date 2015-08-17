package com.ebuy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ebuy.entity.SalesOrderEntity;


/**
 * The Class SalesOrderDao.
 */
@Repository
public class SalesOrderDao extends BaseDao<SalesOrderEntity, Integer> {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(SalesOrderDao.class);

    /**
     * Instantiates a new sales order dao.
     */
    public SalesOrderDao() {
        super(SalesOrderEntity.class);
    }

    /**
     * Find by code.
     *
     * @param code the code
     * @return the sales order entity
     */
    public SalesOrderEntity findByCode(String code) {
        log.debug("Inside findByCode. code {}", code);
        SalesOrderEntity entity = null;
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("code", code);
        List<SalesOrderEntity> salesOrders = findByNamedQuery("findSalesOrderByCode", queryParams);
        if (null != salesOrders && salesOrders.size() == 1) {
            entity = salesOrders.get(0);
        }
        log.debug("Exiting findByCode. code {}", code);
        return entity;
    }

    /* (non-Javadoc)
     * @see com.crossover.ebuy.dao.BaseDao#findAll()
     */
    @Override
    public List<SalesOrderEntity> findAll() {
        log.debug("Inside findAll");
        return findByNamedQuery("findSalesOrders");
    }
}
