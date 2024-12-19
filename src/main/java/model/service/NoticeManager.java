package model.service;

import java.sql.SQLException;

import model.dao.GroupServiceDAO;
import model.domain.Notice;

public class NoticeManager {
    private static NoticeManager noticeMan = new NoticeManager();
    private GroupServiceDAO noticeDao;
    
    private NoticeManager() {
        try {
            noticeDao = new GroupServiceDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static NoticeManager getInstance() {
        return noticeMan;
    }
    
    //공지사항 생성
    public int createNotice (Notice notice) throws SQLException, ExistingGroupException {
        //if (noticeDao.existingNotice(notice.getNoticeId()) == true) {
        //    throw new ExistingNoticeException(notice.getNoticeId() + "는 존재하는 ID입니다.");
        //}
        return noticeDao.createNotice(notice);
    }

}
