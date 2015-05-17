package org.mydomain.academy.services.ServiceInterface;

import org.mydomain.academy.db.entities.Group;

import java.util.List;

public interface GroupService {

	List<Group> findAllGroupsService();

	Group findGroupByIdService(long id);

	List<Group> findGroupByNameService(String name);

	List<Group> findGroupByFormIdService(long id);

	List<Group> findGroupBySpecializationIdService(long id);

	boolean deleteGroupService(Group group);

	boolean saveGroupService(Group group);

	List<Group> findByCriteriaService(int flags, Object... values);
}
