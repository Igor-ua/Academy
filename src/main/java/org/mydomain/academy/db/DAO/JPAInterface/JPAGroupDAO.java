package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Group;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAGroupDAO extends JpaRepository<Group, Long> {

	Page<Group> findByNameOrFormNameOrSpecializationName(
			String groupName,
			String formName,
			String specializationName,
			Pageable pageable);

}