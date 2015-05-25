package org.mydomain.academy.db.DAO.BasicInterface;


import org.mydomain.academy.db.entities.Subject;

import java.util.List;

public interface SubjectDAO {

	int FLAG_ID = 1;
	int FLAG_NAME = FLAG_ID << 1;
	int FLAG_SPECIALIZATION = FLAG_NAME << 1;

	Subject findSubjectById(long id);

	List<Subject> findAllSubjects();

	List<Subject> findSubjectBySpecializationId(long id);

	List<Subject> findSubjectByName(String name);

	boolean saveSubject(Subject subject);

	boolean deleteSubject(Subject subject);

	List<Subject> findByCriteria(int flags, Object... values);
}
