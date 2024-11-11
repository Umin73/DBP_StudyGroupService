package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.domain.Quiz;
import model.domain.User;

public class QuizDAO {
    private ConnectionManager connectionManager = new ConnectionManager();

    // 퀴즈 생성
    public int createQuiz(Quiz quiz) {
        String sql = "INSERT INTO Quiz (quiz_id, title, group_id, createDate, score, section, percent, submitNumber, submitYN, question_id, user_id) " +
                     "VALUES (?, ?, ?, SYSDATE, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, quiz.getQuizId());
            pstmt.setString(2, quiz.getTitle());
            pstmt.setString(3, quiz.getGroupId());
            pstmt.setDouble(4, quiz.getScore());
            pstmt.setString(5, quiz.getSection());
            pstmt.setDouble(6, quiz.getPercent());
            pstmt.setInt(7, quiz.getSubmitNumber());
            pstmt.setString(8, quiz.getSubmitYN());
            pstmt.setString(9, quiz.getQuestionId());
            pstmt.setString(10, quiz.getCreatedBy().getUserId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 퀴즈 조회 (Quiz ID로)
    public Quiz findQuizById(String quizId) {
        String sql = "SELECT * FROM Quiz WHERE quiz_id = ?";
        Quiz quiz = null;

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, quizId);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                quiz = new Quiz(
                    rs.getString("quiz_id"),
                    rs.getString("title"),
                    rs.getString("group_id"),
                    rs.getDouble("score"),
                    rs.getString("section"),
                    rs.getDouble("percent"),
                    rs.getInt("submitNumber"),
                    rs.getString("submitYN"),
                    rs.getString("question_id"),
                    new User(null, rs.getString("user_id"), null, null, null, sql)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quiz;
    }

    // 퀴즈 목록 조회
    public List<Quiz> findAllQuizzes() {
        String sql = "SELECT * FROM Quiz";
        List<Quiz> quizList = new ArrayList<>();

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Quiz quiz = new Quiz(
                    rs.getString("quiz_id"),
                    rs.getString("title"),
                    rs.getString("group_id"),
                    rs.getDouble("score"),
                    rs.getString("section"),
                    rs.getDouble("percent"),
                    rs.getInt("submitNumber"),
                    rs.getString("submitYN"),
                    rs.getString("question_id"),
                    new User(null, rs.getString("user_id"), null, null, null, sql)
                );
                quizList.add(quiz);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quizList;
    }

    // 퀴즈 삭제
    public int deleteQuiz(String quizId) {
        String sql = "DELETE FROM Quiz WHERE quiz_id = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, quizId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // 퀴즈 업데이트
    public int updateQuiz(Quiz quiz) {
        String sql = "UPDATE Quiz SET title = ?, score = ?, section = ?, percent = ?, submitNumber = ?, submitYN = ? WHERE quiz_id = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, quiz.getTitle());
            pstmt.setDouble(2, quiz.getScore());
            pstmt.setString(3, quiz.getSection());
            pstmt.setDouble(4, quiz.getPercent());
            pstmt.setInt(5, quiz.getSubmitNumber());
            pstmt.setString(6, quiz.getSubmitYN());
            pstmt.setString(7, quiz.getQuizId());

            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    

}