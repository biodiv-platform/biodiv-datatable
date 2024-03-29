package com.strandls.dataTable.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
			entity = Boolean.FALSE.equals(entity.getIsRemoved()) ? entity : null;
		} catch (Exception e) {
			logger.info(e.getMessage());
			logger.error(e.toString());
		} finally {
			session.close();
		}
		return entity;
	}

	@SuppressWarnings("unchecked")
	public List<DataTable> getDataTableListByIds(String dataTableType, String orderBy, List<Long> dataTableIds,
			Integer offset, Integer limit) {
		String qry = orderBy == null
				? "from DataTable where  id IN :dataTableIds and dataTableType = :dataTableType and isRemoved = false order by lastRevised desc"
				: "from DataTable where  id IN :dataTableIds and dataTableType = :dataTableType and isRemoved = false order by createdOn desc";
		List<DataTable> result = null;
		Session session = sessionFactory.openSession();
		try {
			Query<DataTable> query = session.createQuery(qry);
			query.setParameter("dataTableIds", dataTableIds);
			query.setParameter("dataTableType", dataTableType);
			query.setFirstResult(offset);
			query.setMaxResults(limit);

			result = query.getResultList();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			session.close();
		}
		return result;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<DataTable> getDataTableList(String dataTableType, String orderBy, Integer limit, Integer offset) {
		Session session = sessionFactory.openSession();
		List<DataTable> observationList = new ArrayList<DataTable>();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<DataTable> cr = cb.createQuery(DataTable.class);
		Root<DataTable> root = cr.from(DataTable.class);
		List<Predicate> predicates = new ArrayList<>();

		predicates.add(cb.equal(root.get("isRemoved"), false));
		if (dataTableType != null && !dataTableType.isEmpty()) {
			predicates.add(cb.equal(root.get("dataTableType"), dataTableType));
			cr.select(root).where(predicates.toArray(new Predicate[] {})).orderBy(cb.desc(root.get(orderBy)));
		} else {
			cr.select(root).where(predicates.toArray(new Predicate[] {})).orderBy(cb.desc(root.get(orderBy)));
		}

		try {
			Query query = session.createQuery(cr);
			query.setFirstResult(offset);
			if (limit != null) {
				query.setMaxResults(limit);
			}
			observationList = query.getResultList();
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
