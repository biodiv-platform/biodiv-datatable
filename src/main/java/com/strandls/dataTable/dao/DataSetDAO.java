package com.strandls.dataTable.dao;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strandls.dataTable.pojo.Dataset;
import com.strandls.dataTable.util.AbstractDAO;

public class DataSetDAO extends AbstractDAO<Dataset, Long> {

	private static final Logger logger = LoggerFactory.getLogger(DataSetDAO.class);

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

	@SuppressWarnings("unchecked")
	public Dataset findDataSetByTitle(String title) {
		Session session = sessionFactory.openSession();
		String qry = "select * from dataset1 where is_deleted = false and title = :keyword limit 1";

		Dataset result = null;
		try {
			Query<Dataset> query = session.createNativeQuery(qry, Dataset.class).setParameter("keyword", title);
			result = query.getSingleResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return result;
	}

}
