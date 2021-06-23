package com.strandls.dataTable.dao;

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
			entity = entity.getDeleted() == false ? entity : null;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.error(e.toString());
		} finally {
			session.close();
		}
		return entity;
	}
	
	@SuppressWarnings({"unchecked","rawtypes"})
	public List<DataTable> getDataTableList(String orderBy ,Integer limit,Integer offset) {
		Session session = sessionFactory.openSession();
		List<DataTable> observationList = new ArrayList<DataTable>();
		String hql = "from DataTable  order by :order desc";
		try {
			Query query = session.createQuery(hql);
			query.setParameter("order", orderBy);
			query.setFirstResult(offset);
			if(limit != null) {
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
	
	

}
