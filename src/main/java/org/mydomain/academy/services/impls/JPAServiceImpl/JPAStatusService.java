package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPAStatusDAO;
import org.mydomain.academy.db.entities.Status;
import org.mydomain.academy.services.ServiceInterface.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JPAStatusService implements StatusService {

    private static final Logger log = Logger.getLogger(JPAStatusService.class);

    @Autowired
    private JPAStatusDAO jpaStatusDAO;

    public Status findStatusByIdService(long id) {
        return jpaStatusDAO.findOne(id);
    }

    public List<Status> findAllStatusService() {
        return jpaStatusDAO.findAll();
    }

    //todo implement
    public Status findLast() {
        return jpaStatusDAO.findLast();
    }

    //todo validation
    private boolean validateStatus(Status status) {
        return true;
    }

    @Override
    public boolean deleteService(Object o) {
        return false;
    }

    @Override
    public boolean saveService(Object object) {
        if (object instanceof Status) {
            try {
                if (validateStatus((Status) object)) {
                    jpaStatusDAO.save((Status) object);
                    return true;
                }
            } catch (RuntimeException e) {
                log.error(e);
            }
        }
        return false;
    }
}