package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAScheduleDAO extends JpaRepository<Schedule, Long> {

	Page<Schedule> findByGroupName(String groupName, Pageable pageable);

}
