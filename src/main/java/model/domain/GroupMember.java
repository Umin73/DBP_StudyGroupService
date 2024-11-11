package model.domain;

import java.util.List;

public class GroupMember {
	private User user;

	
	public GroupMember(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	public void joinGroup(StudyGroup group) {
		group.inviteMember(user.getEmail());
	}
	
	
	public void viewAnnouncements(StudyGroup group) {
        group.getAnnouncements();
    }

    public void viewSchedules(StudyGroup group) {
        
    }

    public void viewAssignments(StudyGroup group) {
        group.getAssignments();
    }

    public void respondToSchedule(Schedule schedule, boolean response) {
        if (response) {
            schedule.addParticipant(user);
        } else {
            schedule.removeParticipant(user);
        }
    }
}