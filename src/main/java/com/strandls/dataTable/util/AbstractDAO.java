package com.strandls.dataTable.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;

public abstract class AbstractDAO<T, K extends Serializable> {

	protected SessionFactory sessionFactory;

	protected Class<? extends T> daoType;

	@SuppressWarnings("unchecked")
	protected AbstractDAO(SessionFactory sessionFactory) {
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
		List<T> entities = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(daoType);
			entities = criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY).list();
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return entities;
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<T> findAll(int limit, int offset) {
		List<T> entities = null;
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(daoType).setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
			entities = criteria.setFirstResult(offset).setMaxResults(limit).list();
		} catch (Exception e) {
			throw e;
		} finally {
			session.close();
		}

		return entities;
	}

}
