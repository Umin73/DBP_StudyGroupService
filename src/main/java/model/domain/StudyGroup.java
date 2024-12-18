package model.domain;

import java.util.Date;
import java.util.List;

public class StudyGroup {
    private String groupId;
    private String groupName;
    private String groupDescription;
    private String goal;
    private String category;
    private int currMembers;
    private int maxMembers;
    private Date startDate;
    private String leaderId;
    private String leaderName;
    private List<GroupMember> members;
    private List<Schedule> schedules;
    private List<Assignment> assignments;
    private List<Notice> Notices;

    public StudyGroup() { } //기본생성자 
    
    /*
    public StudyGroup(String groupId, String groupName, String groupDescription, String goal, String category, int maxMembers, 
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
    
    public StudyGroup(String groupId, String groupName, String groupDescription, String goal, String category, int maxMembers, 
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
    
    public StudyGroup(String groupId, String groupName, String groupDescription, String category) {
        super();
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.category = category;
    }

    public StudyGroup(String groupName, String groupDescription, String goal, String category,
			int maxMembers, String leaderId) {
		super();
		this.groupName = groupName;
		this.groupDescription = groupDescription;
		this.goal = goal;
		this.category = category;
		this.setCurrMembers(1);
		this.maxMembers = maxMembers;
		this.leaderId = leaderId;
	}
    
    public StudyGroup(String groupId, String groupName, String groupDescription, String goal, String category, int currMembers, int maxMembers) {
        super();
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.goal = goal;
        this.category = category;
        this.setCurrMembers(currMembers);
        this.maxMembers = maxMembers;
    }
    
	public StudyGroup(String groupId, String groupName, String groupDescription, String category, int currMembers, int maxMembers) {
        super();
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupDescription = groupDescription;
        this.category = category;
        this.setCurrMembers(currMembers);
        this.maxMembers = maxMembers;
    }

    public void createGroup() {
        
    }
    
    public void updateGroupInfo() {
        
    }
    
    public void inviteMember(String email) {

    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
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

    public List<Notice> getAnnouncements() {
        return Notices;
    }

    public void setAnnouncements(List<Notice> announcements) {
        this.Notices = announcements;
    }

    public int getCurrMembers() {
        return currMembers;
    }

    public void setCurrMembers(int currMembers) {
        this.currMembers = currMembers;
    }

    
}