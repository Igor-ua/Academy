package org.mydomain.academy.services.impls.ServiceImpl;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.SubjectDAO;
import org.mydomain.academy.db.entities.Subject;
import org.mydomain.academy.services.ServiceInterface.SubjectService;

import java.util.List;

public class JDBCSubjectService implements SubjectService {

	private static final Logger log = Logger.getLogger(JDBCSubjectService.class);

	private SubjectDAO subjectDAO;

	public JDBCSubjectService(SubjectDAO subjectDAO) {
		this.subjectDAO = subjectDAO;
		log.info("SubjectService has been initialized.");
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
		return subjectDAO.findAllSubjects();
	}

	@Override
	public Subject findSubjectByIdService(long id) {
		return subjectDAO.findSubjectById(id);
	}

	@Override
	public List<Subject> findSubjectByNameService(String name) {
		return subjectDAO.findSubjectByName(name);
	}

	@Override
	public List<Subject> findSubjectBySpecializationIdService(long id) {
		return subjectDAO.findSubjectBySpecializationId(id);
	}

	@Override
	public boolean deleteSubjectService(Subject subject) {
		return subjectDAO.deleteSubject(subject);
	}

	@Override
	public boolean saveSubjectService(Subject subject) {
		return validateSubject(subject) && subjectDAO.saveSubject(subject);
	}

	@Override
	public List<Subject> findByCriteriaService(int flags, Object... values) {
		return subjectDAO.findByCriteria(flags, values);
	}
}
