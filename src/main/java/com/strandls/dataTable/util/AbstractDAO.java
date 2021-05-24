package com.strandls.dataTable.util;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

public abstract class AbstractDAO<T, K extends Serializable> {
	
	protected SessionFactory sessionFactory;

	protected Class<? extends T> daoType;

	@SuppressWarnings("unchecked")
	protected AbstractDAO(SessionFactory sessionFactory){
		daoType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.sessionFactory = sessionFactory;
	}

	public T save(T entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(entity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}

	public T update(T entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}

	public T delete(T entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.delete(entity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}
		return entity;
	}

	public abstract T findById(K id);

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> findAll() {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(daoType);
		List<T> entities = criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return entities;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> findAll(int limit, int offset) {
		Session session = sessionFactory.openSession();
		Criteria criteria = session.createCriteria(daoType).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		List<T> entities = criteria.setFirstResult(offset).setMaxResults(limit).list();
		return entities;
	}

	@SuppressWarnings("unchecked")
	public T findByPropertyWithCondition(String property, String value, String condition) {
		String queryStr = "" + "from " + daoType.getSimpleName() + " t " + "where t." + property + " " + condition
				+ " :value";
		Session session = sessionFactory.openSession();
		Query<T> query = session.createQuery(queryStr);
		query.setParameter("value", value);

		T entity = null;
		try {
			entity = (T) query.getSingleResult();
		} catch (NoResultException e) {
			throw e;
		}
		session.close();
		return entity;

	}

	@SuppressWarnings("unchecked")
	public List<T> getByPropertyWithCondtion(String property, Object value, String condition, int limit, int offset) {
		String queryStr = "" + "from " + daoType.getSimpleName() + " t " + "where t." + property + " " + condition
				+ " :value" + " order by id";
		Session session = sessionFactory.openSession();
		Query<T> query = session.createQuery(queryStr);
		query.setParameter("value", value);

		List<T> resultList = new ArrayList<T>();
		try {
			if (limit > 0 && offset >= 0)
				query = query.setFirstResult(offset).setMaxResults(limit);
			resultList = query.getResultList();

		} catch (NoResultException e) {
			throw e;
		}
		session.close();
		return resultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> fetchFilteredRecords(String attribute1,String attribute2, 
			Object value1,Object value2, String condition, String orderBy
			) {
		String queryStr = "" + "from " + daoType.getSimpleName() + " t " + " where "+
		"t."+ attribute1 + " " + condition + " :value1"+
		" and "+
		"t."+attribute2 + " " + condition + " :value2"+ 
		" order by "+":orderby"+ " desc";
		
		Session session = sessionFactory.openSession();
		Query<T> query = session.createQuery(queryStr);
		query.setParameter("value1", value1);
		query.setParameter("value2", value2);
		query.setParameter("orderby", orderBy);

		List<T> resultList = new ArrayList<T>();
		try {
			resultList = query.getResultList();
		} catch (NoResultException e) {
			throw e;
		}
		session.close();
		return resultList;
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> fetchFilteredRecordsWithCriteria(String attribute1, String attribute2, 
			List<Long> value1,Object value2, String orderBy, Integer offSet, Integer limit
			) {
		Session session = sessionFactory.openSession();
		try {
		Criteria criteria = session.createCriteria(daoType.getName());
		if(attribute1 != null) {
			criteria.add(Restrictions.in(attribute1, value1));
			}
		if(attribute2 != null)
			criteria.add(Restrictions.eq(attribute2, value2));
		if(orderBy != null)
			criteria.addOrder( Order.desc(orderBy));
		if(offSet != -1)
			criteria.setFirstResult(offSet);
		if(limit != -1)
			criteria.setMaxResults(limit);
		List<T> resultList = criteria.list();
		return resultList;
		}
		catch (Exception e) {
		}
		finally {
			session.close();
		}
		return null;
	}

}
