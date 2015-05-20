package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Form;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Specialization;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAGroupDAO extends JpaRepository<Group, Long> {

	Page<Group> findByNameOrFormOrSpecialization(String name, Form form, Specialization specialization, Pageable pageable);

}