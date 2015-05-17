package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface JPAMarkDAO extends JpaRepository<Mark, Long> {

	List<Mark> findByDateOrMarkTypeOrTeacherOrStudentOrGroupOrSubjectOrForm(
			Date date, MarkType markType, Teacher teacher,
			Student student, Group group, Subject subject, Form form
	);

}
