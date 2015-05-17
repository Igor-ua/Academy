package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Specialization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPASpecializationDAO extends JpaRepository<Specialization, Long> {

	List<Specialization> findByName(String name);

}
