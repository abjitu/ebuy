package com.ebuy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ebuy.entity.ProductEntity;


/**
 * The Class ProductDao.
 */
@Repository
public class ProductDao extends BaseDao<ProductEntity, Integer> {

    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ProductDao.class);
    
    /**
     * Instantiates a new product dao.
     */
    public ProductDao() {
        super(ProductEntity.class);
    }
    
    /**
     * Find by code.
     *
     * @param code the code
     * @return the product entity
     */
    public ProductEntity findByCode(String code) {
        log.debug("Inside findByCode. code {}", code);
        ProductEntity entity = null;
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("code", code);
        List<ProductEntity> products = findByNamedQuery("findProductByCode", queryParams);
        if (null != products && products.size() == 1) {
            entity = products.get(0);
        }
        log.debug("Exiting findByCode. code {}", code);
        return entity;
    }

    /* (non-Javadoc)
     * @see com.crossover.ebuy.dao.BaseDao#findAll()
     */
    @Override
    public List<ProductEntity> findAll() {
        log.debug("Inside findAll");
        return findByNamedQuery("findProducts");
    }
}
