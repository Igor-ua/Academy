package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Form;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAFormDAO extends JpaRepository<Form, Long> {

	Page<Form> findByName(String name, Pageable pageable);

}