package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.StudyGroupDAO;
import model.domain.StudyGroup;

public class GroupManager {
    private static GroupManager groupMan = new GroupManager();
    private StudyGroupDAO groupDao;
    
    private GroupManager() {
        try {
            groupDao = new StudyGroupDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static GroupManager getInstance() {
        return groupMan;
    }
    
    public int create(StudyGroup group, String userId) throws SQLException, ExistingGroupException {
        if (groupDao.existingGroup(group.getGroupId()) == true) {
            throw new ExistingGroupException(group.getGroupId() + "는 존재하는 그룹 ID입니다.");
        }
        return groupDao.create(group, userId);
    }
    
    public List<StudyGroup> findAllGroups() throws SQLException {
        return groupDao.findGroupList();
    }
}