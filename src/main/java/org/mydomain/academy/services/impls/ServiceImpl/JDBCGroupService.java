package org.mydomain.academy.services.impls.ServiceImpl;

import org.apache.log4j.Logger;
import org.mydomain.academy.db.DAO.BasicInterface.GroupDAO;
import org.mydomain.academy.db.entities.Group;
import org.mydomain.academy.services.ServiceInterface.GroupService;

import java.util.List;

public class JDBCGroupService implements GroupService {

	private static final Logger log = Logger.getLogger(JDBCGroupService.class);

	private GroupDAO groupDAO;

	public JDBCGroupService(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
		log.info("GroupService has been initialized.");
	}

	private boolean validateGroup(Group group) {
		if (!group.getName().matches("[ A-Za-zР-пр-џ\\-]{0,}")) {
			log.error("Group validation error.");
			return false;
		}
		return true;
	}

	@Override
	public List<Group> findAllGroupsService() {
		return groupDAO.findAllGroups();
	}

	@Override
	public Group findGroupByIdService(long id) {
		return groupDAO.findGroupById(id);
	}

	@Override
	public List<Group> findGroupByNameService(String name) {
		return groupDAO.findGroupByName(name);
	}

	@Override
	public List<Group> findGroupByFormIdService(long id) {
		return groupDAO.findGroupByFormId(id);
	}

	@Override
	public List<Group> findGroupBySpecializationIdService(long id) {
		return groupDAO.findGroupBySpecializationId(id);
	}

	@Override
	public boolean deleteGroupService(Group group) {
		return groupDAO.deleteGroup(group);
	}

	@Override
	public boolean saveGroupService(Group group) {
		return validateGroup(group) && groupDAO.saveGroup(group);
	}

	@Override
	public List<Group> findByCriteriaService(int flags, Object... values) {
		return groupDAO.findByCriteria(flags, values);
	}
}
