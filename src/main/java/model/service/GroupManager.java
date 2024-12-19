package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.GroupMemberDAO;
import model.dao.StudyGroupDAO;
import model.domain.GroupMember;
import model.domain.StudyGroup;

public class GroupManager {
    private static GroupManager groupMan = new GroupManager();
    private StudyGroupDAO groupDao;
    private GroupMemberDAO memberDao;
    
    private GroupManager() {
        try {
            groupDao = new StudyGroupDAO();
            memberDao = new GroupMemberDAO();
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
    
    public StudyGroup findGroup(String groupId) throws SQLException {
        return groupDao.findGroupById(groupId);
    }
    
    public List<StudyGroup> findAllGroups() throws SQLException {
        return groupDao.findGroupList();
    }
    
    public List<StudyGroup> getUserGroups(String userId) throws SQLException {
        return groupDao.getUserGroups(userId);
    }
    
    public List<GroupMember> getGroupMemberList(String groupId) throws SQLException {
        return memberDao.findMembersByGroupId(groupId);
    }
}