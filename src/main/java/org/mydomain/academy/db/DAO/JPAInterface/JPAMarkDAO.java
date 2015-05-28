package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface JPAMarkDAO extends JpaRepository<Mark, Long> {

	Page<Mark> findByTeacherPersonNameOrStudentPersonName(
			String teacherName,
			String studentName,
			Pageable pageable
	);

}