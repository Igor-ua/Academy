package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPAMarkDAO;
import org.mydomain.academy.db.entities.*;
import org.mydomain.academy.services.ServiceInterface.MarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JPA;

@Component
public class JPAMarkService implements MarkService {

	private static final Logger log = Logger.getLogger(JPAMarkService.class);

	private JPAMarkDAO jpaMarkDAO;

	@Autowired
	public void setJpaMarkDAO(JPAMarkDAO jpaMarkDAO) {
		this.jpaMarkDAO = jpaMarkDAO;
	}

	public JPAMarkService() {
	}

	private boolean validateMark(Mark mark) {
		if (mark.getDate().after(new Date())) {
			log.error("Mark validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Mark> findAllMarksService() {
		return jpaMarkDAO.findAll();
	}

	@Override
	public Mark findMarkByIdService(long id) {
		return jpaMarkDAO.findOne(id);
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByMarkTypeIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByTeacherIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByStudentIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByGroupIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkBySubjectIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByFormIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Mark> findMarkByDateService(Date date) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	public boolean deleteMarkService(Mark mark) {
		try {
			jpaMarkDAO.delete(mark);
			return true;
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	public boolean saveMarkService(Mark mark) {
		try {
			if (validateMark(mark)) {
				jpaMarkDAO.save(mark);
				return true;
			}
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	@Deprecated
	public List<Mark> findByCriteriaService(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	public List<Mark> findByAny(Date date, MarkType markType, Teacher teacher, Student student,
								Group group, Subject subject, Form form) {
		return jpaMarkDAO.findByDateOrMarkTypeOrTeacherOrStudentOrGroupOrSubjectOrForm(
				date, markType, teacher, student, group, subject, form);
	}
}
