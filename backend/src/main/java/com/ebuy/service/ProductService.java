package com.ebuy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.commons.request.Product;
import com.ebuy.dao.ProductDao;
import com.ebuy.entity.ProductEntity;
import com.ebuy.util.BeanUtils;


/**
 * The Class ProductService.
 */
@Service
public class ProductService {
    
    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ProductService.class);

    /** The product dao. */
    private @Autowired ProductDao productDao;

    /**
     * Creates the.
     *
     * @param entity the entity
     * @return the product
     */
    @Transactional(rollbackFor = Exception.class)
    public Product create(ProductEntity entity) {
        log.debug("Inside create method");
        ProductEntity product = readByCode(entity.getCode());
        if (null != product) {
            log.info("Product already exists. Hence updating");
            product.setCode(entity.getCode());
            product.setDescription(entity.getDescription());
            product.setQuantity(entity.getQuantity());
            product.setUnitPrice(entity.getUnitPrice());
            productDao.update(product);
        } else {
            log.info("Product doesnot exists. Hence creating new");
            entity.setStatus('A');
            productDao.persist(entity);
        }
        return BeanUtils.convert(entity);
    }

    /**
     * Read by code.
     *
     * @param code the code
     * @return the product entity
     */
    @Transactional(readOnly = true)
    private ProductEntity readByCode(String code) {
        log.debug("Inside readByCode method. code : {}", code);
        return productDao.findByCode(code);
    }

    /**
     * Gets the.
     *
     * @param code the code
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<Product> get(String code) {
        log.debug("Inside get method. code : {}", code);
        List<Product> result = new ArrayList<Product>();
        if (StringUtils.isBlank(code)) {
            List<ProductEntity> products = productDao.findAll();
            if (null != products && products.size() > 0) {
                for (ProductEntity entity : products) {
                    result.add(BeanUtils.convert(entity));
                }
            }
        } else {
            result.add(BeanUtils.convert(readByCode(code)));
        }
        return result;
    }

    /**
     * Delete.
     *
     * @param code the code
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(String code) {
        ProductEntity entity = productDao.findByCode(code);
        entity.setStatus('I');
    }
}
