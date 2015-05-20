package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Specialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPASpecializationDAO extends JpaRepository<Specialization, Long> {

	Page<Specialization> findByName(String name, Pageable pageable);

}
