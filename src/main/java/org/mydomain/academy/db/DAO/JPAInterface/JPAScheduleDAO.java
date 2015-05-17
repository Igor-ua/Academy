package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Schedule;
import org.mydomain.academy.db.entities.Subject;
import org.mydomain.academy.db.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAScheduleDAO extends JpaRepository<Schedule, Long> {

	List<Schedule> findBySubjectOrTeacherOrGroupOrDayOrChisZnamOrLenta(
			Subject subject, Teacher teacher, Group group,
			String day, String chisZnam, Integer lenta
	);


}
