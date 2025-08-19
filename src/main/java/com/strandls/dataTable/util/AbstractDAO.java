package com.strandls.dataTable.util;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDAO<T, K extends Serializable> {

	protected final SessionFactory sessionFactory;

	protected final Class<T> daoType;

	@SuppressWarnings("unchecked")
	protected AbstractDAO(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.daoType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public T save(T entity) {
		return executeInTransaction(session -> {
			session.save(entity);
			return entity;
		});
	}

	public T update(T entity) {
		return executeInTransaction(session -> {
			session.update(entity);
			return entity;
		});
	}

	public T delete(T entity) {
		return executeInTransaction(session -> {
			session.delete(entity);
			return entity;
		});
	}

	public abstract T findById(K id);

	public List<T> findAll() {
		try (Session session = sessionFactory.openSession()) {
			String hql = "FROM " + daoType.getSimpleName();
			return session.createQuery(hql, daoType).list();
		}
	}

	public List<T> findAll(int limit, int offset) {
		try (Session session = sessionFactory.openSession()) {
			String hql = "FROM " + daoType.getSimpleName();
			return session.createQuery(hql, daoType).setFirstResult(offset).setMaxResults(limit).list();
		}
	}

	// Generic transaction execution wrapper
	protected <R> R executeInTransaction(HibernateTransaction<R> action) {
		Transaction tx = null;
		try (Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			R result = action.execute(session);
			tx.commit();
			return result;
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		}
	}

	@FunctionalInterface
	public interface HibernateTransaction<R> {
		R execute(Session session);
	}
}
