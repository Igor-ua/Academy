package org.mydomain.academy.db.DAO.JPAInterface;


import org.mydomain.academy.db.entities.Form;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAFormDAO extends JpaRepository<Form, Long> {

	List<Form> findByName(String name);

}