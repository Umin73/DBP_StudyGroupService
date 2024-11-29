package model.domain;

import java.util.Date;



public class Assignment {
	private String assignmentId;
	private String title;
	private String description;
	private Date deadline;
	private String groupId;
	
	public Assignment(String assignmentId, String title, String description, Date deadline, String groupId) {
		super();
		this.assignmentId = assignmentId;
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.groupId = groupId;
	}
	
	public String getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(String assignmentId) {
		this.assignmentId = assignmentId;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public void submitAssignment(User user) {
		
	}
	
	public String getTitle() {
        return title;
    }
	
	public void setTitle(String title) {
		this.title = title;
	}

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
    	this.description = description;
    }
    
    public Date getDeadline() {
    	return deadline;
    }
    
    public void setDeadline(Date deadline) {
    	this.deadline = deadline;
    }
}