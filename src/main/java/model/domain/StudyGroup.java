package model.domain;

import java.util.Date;
import java.util.List;

public class StudyGroup {
    private int groupId;
    private String groupName;
    private String groupDescription;
    private String goal;
    private String category;
    private int maxMembers;
    private Date startDate;
    private String leaderId;
    private String leaderName;
    private List<GroupMember> members;
    private List<Schedule> schedules;
    private List<Assignment> assignments;
    private List<Announcement> announcements;

    public StudyGroup() { }
    
    /*
    public StudyGroup(int groupId, String groupName, String groupDescription, String goal, String category, int maxMembers, 
            Date startDate, String leaderId, String leaderName,
            List<GroupMember> members, List<Schedule> schedules, List<Assignment> assignments, List<Announcement> announcements) {
        super();
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.goal = goal;
        this.category = category;
        this.maxMembers = maxMembers;
        this.startDate = startDate;
        this.leaderId = leaderId;
        this.leaderName = leaderName;
        this.members = members;
        this.schedules = schedules;
        this.assignments = assignments;
        this.announcements = announcements;
    }
    */
    
    public StudyGroup(int groupId, String groupName, String groupDescription, String goal, String category, int maxMembers, 
            Date startDate, String leaderId, String leaderName) {
        super();
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.goal = goal;
        this.category = category;
        this.maxMembers = maxMembers;
        this.startDate = startDate;
        this.leaderId = leaderId;
        this.leaderName = leaderName;
    }
    
    public StudyGroup(int groupId, String groupName, String groupDescription, String category) {
        super();
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.category = category;
    }

    public void createGroup() {
        
    }
    
    public void updateGroupInfo() {
        
    }
    
    public void inviteMember(String email) {

    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDescription() {
        return groupDescription;
    }

    public void setGroupDescription(String groupDescription) {
        this.groupDescription = groupDescription;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public List<GroupMember> getMembers() {
        return members;
    }

    public void setMembers(List<GroupMember> members) {
        this.members = members;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Announcement> getAnnouncements() {
        return announcements;
    }

    public void setAnnouncements(List<Announcement> announcements) {
        this.announcements = announcements;
    }

    
}