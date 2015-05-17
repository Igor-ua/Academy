package org.mydomain.academy.services.ServiceInterface;

import org.mydomain.academy.db.entities.Subject;

import java.util.List;

public interface SubjectService {

	List<Subject> findAllSubjectsService();

	Subject findSubjectByIdService(long id);

	List<Subject> findSubjectByNameService(String name);

	List<Subject> findSubjectBySpecializationIdService(long id);

	boolean deleteSubjectService(Subject subject);

	boolean saveSubjectService(Subject subject);

	List<Subject> findByCriteriaService(int flags, Object... values);
}