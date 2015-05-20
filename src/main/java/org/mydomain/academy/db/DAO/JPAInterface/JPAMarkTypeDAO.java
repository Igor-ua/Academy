package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.MarkType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JPAMarkTypeDAO extends JpaRepository<MarkType, Long> {

	Page<MarkType> findByName(String name, Pageable pageable);

}