package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Date;
import java.util.List;

public interface JPAPersonDAO extends JpaRepository<Person, Long> {

	Page<Person> findByNameOrBirthdayOrPassport(
			String name,
			Date birthday,
			String passport,
			Pageable pageable);

}