package com.ebuy.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * The Class BaseDao.
 *
 * @param <T> the generic type
 * @param <PK> the generic type
 */
public class BaseDao<T, PK extends Serializable> {
    
    /** The Constant log. */
    protected static final Logger log = LoggerFactory.getLogger(BaseDao.class);

    /** The persistent class. */
    protected Class<T> persistentClass;
    
    /** The entity manager. */
    protected @PersistenceContext EntityManager entityManager;

    /**
     * Instantiates a new base dao.
     *
     * @param persistentClass the persistent class
     */
    public BaseDao(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Find.
     *
     * @param id the id
     * @return the t
     */
    public T find(PK id) {
        return entityManager.find(persistentClass, id);
    }

    /**
     * Persist.
     *
     * @param entity the entity
     */
    public void persist(T entity) {
        entityManager.persist(entity);
    }

    /**
     * Update.
     *
     * @param entity the entity
     * @return the t
     */
    public T update(T entity) {
        return entityManager.merge(entity);
    }

    /**
     * Removes the.
     *
     * @param entity the entity
     */
    public void remove(T entity) {
        entityManager.remove(entity);
    }

    /**
     * Removes the.
     *
     * @param id the id
     */
    public void remove(PK id) {
        entityManager.remove(find(id));
    }

    /**
     * Find all.
     *
     * @return the list
     */
    public List<T> findAll() {
        log.debug("finding all {} instances", this.persistentClass);

        String sql = "from " + this.persistentClass.getName() + " model order by model.id desc";

        Query query = entityManager.createQuery(sql);

        @SuppressWarnings("unchecked")
        List<T> list = query.getResultList();

        return list;
    }

    /**
     * Find by property.
     *
     * @param propertyName the property name
     * @param value the value
     * @return the list
     */
    public List<T> findByProperty(String propertyName, Object value) {
        log.debug("finding {} instance with property: {}, value : {}", this.persistentClass, propertyName, value);

        String sql = "from " + this.persistentClass.getName() + " model where model." + propertyName + " = :"
                + propertyName;

        Query query = entityManager.createQuery(sql, persistentClass);
        query.setParameter(propertyName, value);

        @SuppressWarnings("unchecked")
        List<T> list = query.getResultList();

        return list;
    }

    /**
     * Find unique by property.
     *
     * @param propertyName the property name
     * @param value the value
     * @return the t
     */
    public T findUniqueByProperty(String propertyName, Object value) {
        log.debug("finding Unique {} instance with property: {}, value : {}", this.persistentClass, propertyName, value);

        T result = null;
        List<T> list = findByProperty(propertyName, value);
        if (null != list && list.size() == 1) {
            result = list.get(0);
        }

        return result;
    }

    /**
     * Find by named query.
     *
     * @param queryName the query name
     * @param queryParams the query params
     * @param page the page
     * @param size the size
     * @return the list
     */
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams, Integer page, Integer size) {
        TypedQuery<T> namedQuery = entityManager.createNamedQuery(queryName, persistentClass);
        if (null != queryParams) {
            for (String s : queryParams.keySet()) {
                Object obj = queryParams.get(s);
                namedQuery.setParameter(s, obj);
            }
        }
        if (page != null && page > 0 && size != null) {
            namedQuery.setFirstResult((page - 1) * size);
            namedQuery.setMaxResults(size);
        }

        return namedQuery.getResultList();
    }

    /**
     * Find by named query.
     *
     * @param queryName the query name
     * @return the list
     */
    public List<T> findByNamedQuery(String queryName) {
        return findByNamedQuery(queryName, null, null, null);
    }

    /**
     * Find by named query.
     *
     * @param queryName the query name
     * @param queryParams the query params
     * @return the list
     */
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        return findByNamedQuery(queryName, queryParams, null, null);
    }

}