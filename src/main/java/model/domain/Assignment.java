package model.domain;

import java.util.Date;

import model.domain.User;

public class Assignment {
	private String title;
	private String description;
	private Date deadline;
	
	
	public Assignment(String title, String description, Date deadline) {
		super();
		this.title = title;
		this.description = description;
		this.deadline = deadline;
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