package model.service;

import model.dao.JDBCUtil;
import model.domain.StudyGroup;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyPageService {

    // 유저가 참가 중인 그룹 리스트 조회
	public List<StudyGroup> getUserGroups(String userId) throws SQLException {
	    JDBCUtil jdbcUtil = new JDBCUtil();
	    List<StudyGroup> groupList = new ArrayList<>();

	    String sql = "SELECT sg.GROUP_ID, sg.GROUPNAME, sg.GROUPDESCRIPTION, sg.GOAL, sg.CATEGORY, sg.MAXMEMBER, sg.CURRMEMBER " +
	                 "FROM GROUPMEMBER gm " +
	                 "JOIN STUDYGROUP sg ON gm.GROUP_ID = sg.GROUP_ID " +
	                 "WHERE gm.USER_ID = ?";

	    jdbcUtil.setSqlAndParameters(sql, new Object[]{userId});

	    try {
	        System.out.println("Executing SQL: " + sql + " with userId=[" + userId + "]");
	        ResultSet rs = jdbcUtil.executeQuery();
	        while (rs.next()) {
	        	StudyGroup group = new StudyGroup(
            		    rs.getString("GROUP_ID"),
            		    rs.getString("GROUPNAME"),
            		    rs.getString("GROUPDESCRIPTION"),
            		    rs.getString("CATEGORY"),
            		    rs.getInt("CURRMEMBER"),
            		    rs.getInt("MAXMEMBER")
            		);
	            System.out.println("Found Group: " + group.getGroupName());
	            groupList.add(group);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        jdbcUtil.close();
	    }

	    return groupList;
	}

}
