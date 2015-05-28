package org.mydomain.academy.services.impls.JPAServiceImpl;

import org.apache.commons.lang3.NotImplementedException;
import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.JPAInterface.JPAGroupDAO;
import org.mydomain.academy.db.entities.Form;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.db.entities.Specialization;
import org.mydomain.academy.services.ServiceInterface.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.mydomain.academy.db.utils.LogMessages.LOG_NOT_IMPLEMENTED_IN_JPA;

@Component
public class JPAGroupService implements GroupService {

	private static final Logger log = Logger.getLogger(JPAGroupService.class);

	private JPAGroupDAO jpaGroupDAO;

	@Autowired
	public void setJpaGroupDAO(JPAGroupDAO jpaGroupDAO) {
		this.jpaGroupDAO = jpaGroupDAO;
	}

	public JPAGroupService() {
	}

	private boolean validateGroup(Group group) {
		if (!group.getName().matches("[ A-Za-zР-пр-џ0-9\\-]{0,}")) {
			log.error("Group validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Group> findAllGroupsService() {
		return jpaGroupDAO.findAll();
	}

	public Page<Group> findAllGroupsService(Pageable pageable) {
		return jpaGroupDAO.findAll(pageable);
	}

	@Override
	public Group findGroupByIdService(long id) {
		return jpaGroupDAO.findOne(id);
	}

	@Override
	@Deprecated
	public List<Group> findGroupByNameService(String name) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Group> findGroupByFormIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	@Deprecated
	public List<Group> findGroupBySpecializationIdService(long id) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	@Override
	public boolean deleteService(Object object) {
		if (object instanceof Group) {
			try {
				jpaGroupDAO.delete((Group) object);
				return true;
			} catch (RuntimeException e) {
				log.error(e);
			}
		}
		return false;
	}

	@Override
	public boolean saveService(Object object) {
		if (object instanceof Group) {
			try {
				if (validateGroup((Group) object)) {
					jpaGroupDAO.save((Group) object);
					return true;
				}
			} catch (RuntimeException e) {
				log.error(e);
			}
		}
		return false;
	}

	@Override
	@Deprecated
	public List<Group> findByCriteriaService(int flags, Object... values) {
		throw new NotImplementedException(LOG_NOT_IMPLEMENTED_IN_JPA.toString());
	}

	public Page<Group> findByAny(String groupName, String formName, String specializationName, Pageable pageable) {
		return jpaGroupDAO.findByNameOrFormNameOrSpecializationName(groupName, formName, specializationName, pageable);
	}
}
