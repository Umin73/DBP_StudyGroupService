package model.domain;

import java.util.Date;

public class Announcement {
	private String content;
	private Date date;
	
	public Announcement(String content) {
        this.content = content;
        this.date = new Date();
    }
	
	public void postAnnouncement() {
		
	}
	
	public String getContent() {
        return content;
    }
	
	public void setContent(String content) {
		this.content = content;
	}

    public Date getDate() {
        return date;
    }
    
}