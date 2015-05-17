package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPASubjectDAO;
import org.mydomain.academy.db.entities.Specialization;
import org.mydomain.academy.db.entities.Subject;
import org.mydomain.academy.services.ServiceInterface.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JPA;

@Component
public class JPASubjectService implements SubjectService{

	private static final Logger log = Logger.getLogger(JPASubjectService.class);

	private JPASubjectDAO jpaSubjectDAO;

	@Autowired
	public void setJpaSubjectDAO(JPASubjectDAO jpaSubjectDAO) {
		this.jpaSubjectDAO = jpaSubjectDAO;
	}

	public JPASubjectService() {
	}

	private boolean validateSubject(Subject subject) {
		if (!subject.getName().matches("[ 0-9A-Za-zР-пр-џ\\-]{0,}")) {
			log.error("Subject validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Subject> findAllSubjectsService() {
		return jpaSubjectDAO.findAll();
	}

	@Override
	public Subject findSubjectByIdService(long id) {
		return jpaSubjectDAO.findOne(id);
	}

	@Override
	@Deprecated
	public List<Subject> findSubjectByNameService(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Subject> findSubjectBySpecializationIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	public boolean deleteSubjectService(Subject subject) {
		try {
			jpaSubjectDAO.delete(subject);
			return true;
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	public boolean saveSubjectService(Subject subject) {
		try {
			if (validateSubject(subject)) {
				jpaSubjectDAO.save(subject);
				return true;
			}
		} catch (RuntimeException e) {
			log.error(e);
		}
		return false;
	}

	@Override
	@Deprecated
	public List<Subject> findByCriteriaService(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	public List<Subject> findByAny(String name, Specialization specialization) {
		return jpaSubjectDAO.findByNameOrSpecialization(name, specialization);
	}
}
