package org.mydomain.academy.db.DAO.JPAInterface;

import org.mydomain.academy.db.entities.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JPAStatusDAO extends JpaRepository<Status, Long> {

    @Query(value = "SELECT * FROM STATUS_SQL WHERE ID = (SELECT MAX(ID) FROM STATUS_SQL)", nativeQuery = true)
    Status findLast();
}