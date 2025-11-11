package com.strandls.dataTable.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strandls.dataTable.pojo.Dataset;
import com.strandls.dataTable.util.AbstractDAO;

import jakarta.inject.Inject;

public class DataSetDAO extends AbstractDAO<Dataset, Long> {

	private final Logger logger = LoggerFactory.getLogger(DataSetDAO.class);

	@Inject
	protected DataSetDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public Dataset findById(Long id) {
		Session session = sessionFactory.openSession();
		Dataset entity = null;
		try {
			entity = session.get(Dataset.class, id);
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.error(e.toString());
		} finally {
			session.close();
		}
		return entity;
	}

	public Dataset findDataSetByTitle(String title) {
		Session session = sessionFactory.openSession();
		String hql = "from Dataset d where d.deleted = false and d.title = :keyword";

		Dataset result = null;
		try {
			Query<Dataset> query = session.createQuery(hql, Dataset.class)
					.setParameter("keyword", title)
					.setMaxResults(1);
			result = query.uniqueResult();
		} catch (Exception e) {
			logger.error("Error finding dataset by title: {}", title, e);
		} finally {
			session.close();
		}
		return result;
	}

}
