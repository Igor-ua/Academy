package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPASubjectDAO extends JpaRepository<Subject, Long> {

	Page<Subject> findByNameOrSpecializationName(
			String subjectName,
			String specializationName,
			Pageable pageable);

}
