package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface JPATeacherDAO extends JpaRepository<Teacher, Long> {

	Page<Teacher> findByPersonNameOrStartOrFinish(String name, Date start, Date finish, Pageable pageable);
}