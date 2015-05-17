package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Specialization;
import org.mydomain.academy.db.entities.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPASubjectDAO extends JpaRepository<Subject, Long> {

	List<Subject> findByNameOrSpecialization(String name, Specialization specialization);

}
