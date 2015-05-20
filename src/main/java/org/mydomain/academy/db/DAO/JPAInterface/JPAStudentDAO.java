package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Person;
import org.mydomain.academy.db.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface JPAStudentDAO extends JpaRepository<Student, Long> {

	Page<Student> findByPersonOrGroupOrStartOrFinish(
			Person person, Group group, Date start, Date finish, Pageable pageable
	);

}