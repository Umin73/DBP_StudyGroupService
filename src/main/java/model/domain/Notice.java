package model.domain;

import java.util.Date;

public class Notice {
    private String noticeId;
    private String title;
    private String content;
    private Date createDate;
    private String reply;
    private String groupId;
    
    public Notice(String noticeId, String title, String content, Date createDate, String reply, String groupId) {
        super();
        this.noticeId = noticeId;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.reply = reply;
        this.groupId = groupId;
    }

    public String getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(String noticeId) {
        this.noticeId = noticeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }
    
    
    
}