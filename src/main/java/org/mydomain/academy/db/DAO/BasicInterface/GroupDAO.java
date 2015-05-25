package org.mydomain.academy.db.DAO.BasicInterface;


import org.mydomain.academy.db.entities.Group;

import java.util.List;

public interface GroupDAO {

	int FLAG_ID = 1;                            // 0001
	int FLAG_NAME = FLAG_ID << 1;               // 0010
	int FLAG_FORM = FLAG_NAME << 1;             // 0100
	int FLAG_SPECIALIZATION = FLAG_FORM << 1;   // 1000

	Group findGroupById(long id);

	List<Group> findAllGroups();

	List<Group> findGroupByFormId(long id);

	List<Group> findGroupBySpecializationId(long id);

	List<Group> findGroupByName(String name);

	boolean saveGroup(Group group);

	boolean deleteGroup(Group group);

	List<Group> findByCriteria(int flags, Object... values);
}
