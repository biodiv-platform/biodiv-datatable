package com.strandls.dataTable.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.strandls.dataTable.pojo.DataTable;
import com.strandls.dataTable.util.AbstractDAO;

public class DataTableDAO extends AbstractDAO<DataTable, Long> {

	private final Logger logger = LoggerFactory.getLogger(DataTableDAO.class);

	@Inject
	protected DataTableDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public DataTable findById(Long id) {
		Session session = sessionFactory.openSession();
		DataTable entity = null;
		try {
			entity = session.get(DataTable.class, id);
			entity = Boolean.FALSE.equals(entity.getDeleted()) ? entity : null;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.error(e.toString());
		} finally {
			session.close();
		}
		return entity;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DataTable> getDataTableList(String orderBy, Integer limit, Integer offset) {
		Session session = sessionFactory.openSession();
		List<DataTable> observationList = new ArrayList<DataTable>();
		String hql = "from DataTable where is_deleted = false  order by :orderBy desc";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("orderBy", orderBy);
			query.setFirstResult(offset);
			if (limit != null) {
				query.setMaxResults(limit);
			}
			observationList = query.list();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		} finally {
			session.close();
		}
		return observationList;
	}

	@SuppressWarnings("unchecked")
	public Long findTotalDataTable() {

		Session session = sessionFactory.openSession();
		String qry = "select count(id) from data_table  where is_deleted = false";

		Long total = null;
		try {
			Query<BigInteger> query = session.createNativeQuery(qry);
			total = query.getSingleResult().longValue();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return total;
	}

}
