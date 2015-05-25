package org.mydomain.academy.db.DAO.impls.hibernate;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.mydomain.academy.db.DAO.BasicInterface.MarkDAO;
import org.mydomain.academy.db.entities.Mark;
import org.mydomain.academy.db.utils.HibernateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_HIBERNATE;

public class HibernateMarkDAOImpl implements MarkDAO {

	private static final Logger log = Logger.getLogger(HibernateMarkDAOImpl.class);

	private HibernateUtils hibernateUtils;

	public HibernateMarkDAOImpl() {
	}

	public HibernateMarkDAOImpl(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Autowired
	public void setHibernateUtils(HibernateUtils hibernateUtils) {
		this.hibernateUtils = hibernateUtils;
	}

	@Override
	public Mark findMarkById(long id) {
		Session session = hibernateUtils.getSession();
		return (Mark) session.get(Mark.class, id);
	}

	@Override
	public List<Mark> findAllMarks() {
		Session session = hibernateUtils.getSession();
		return session.createQuery("from Mark").list();
	}

	@Override
	public boolean saveMark(Mark mark) {
		return saveOrDelete(mark, false);
	}

	@Override
	public boolean deleteMark(Mark mark) {
		return saveOrDelete(mark, true);
	}

	private boolean saveOrDelete(Mark m, boolean delete) {
		Session session = hibernateUtils.getSession();
		try {
			Transaction t = session.beginTransaction();
			if (delete) {
				session.delete(m);
			} else {
				session.merge(m);
			}
			t.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	@Override
	public List<Mark> findByCriteria(int flags, Object... values) {
		Session session = hibernateUtils.getSession();
		Criteria criteria = session.createCriteria(Mark.class);

		for (Object value : values) {
			if ((flags & FLAG_ID) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("id", value));
				}
				flags = flags ^ FLAG_ID;
			} else if ((flags & FLAG_MARK_TYPE) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("markType_id", value));
				}
				flags = flags ^ FLAG_MARK_TYPE;
			} else if ((flags & FLAG_TEACHER) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("teacher_id", value));
				}
				flags = flags ^ FLAG_TEACHER;
			} else if ((flags & FLAG_STUDENT) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("student_id", value));
				}
				flags = flags ^ FLAG_STUDENT;
			} else if ((flags & FLAG_GROUP) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("group_id", value));
				}
				flags = flags ^ FLAG_GROUP;
			} else if ((flags & FLAG_SUBJECT) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("subject_id", value));
				}
				flags = flags ^ FLAG_SUBJECT;
			} else if ((flags & FLAG_FORM) != 0) {
				if (!value.equals(0L)) {
					criteria.add(Restrictions.eq("form_id", value));
				}
				flags = flags ^ FLAG_FORM;
			} else if ((flags & FLAG_DATE) != 0) {
				if (value != null) {
					criteria.add(Restrictions.eq("date", value));
				}
				flags = flags ^ FLAG_DATE;
			}

		}
		return criteria.list();
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByTeacherId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByStudentId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByGroupId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkBySubjectId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByFormId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByMarkTypeId(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByDate(Date date) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_HIBERNATE.toString());
	}
}
