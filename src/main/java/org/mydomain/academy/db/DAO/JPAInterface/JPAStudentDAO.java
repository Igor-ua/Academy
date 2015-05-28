package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAStudentDAO extends JpaRepository<Student, Long> {

	Page<Student> findByPersonNameOrGroupName(String personName, String groupName, Pageable pageable);

}