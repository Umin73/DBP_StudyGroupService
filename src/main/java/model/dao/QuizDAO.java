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
        String sql = "INSERT INTO Quiz (quiz_id, title, group_id, createDate, section, percent, submitNumber, submitYN, user_id) " +
                     "VALUES (?, ?, ?, SYSDATE, ?, ?, ?, ?, ?)";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, quiz.getQuizId());
            pstmt.setString(2, quiz.getTitle());
            //pstmt.setString(3, quiz.getGroupId());
            pstmt.setString(3, quiz.getGroupId() != null ? quiz.getGroupId() : "3d085fa1c83a41b9a19cda7a41cb19d7");
            pstmt.setString(4, quiz.getSection());
            pstmt.setDouble(5, quiz.getPercent());
            pstmt.setInt(6, quiz.getSubmitNumber());
            pstmt.setString(7, quiz.getSubmitYN());
            //pstmt.setString(8, quiz.getQuestionId());
            //pstmt.setString(9, quiz.getCreatedBy().getUserId());
            pstmt.setString(8, (quiz.getCreatedBy() != null) ? quiz.getCreatedBy().getUserId() : "test1");

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
                    rs.getString("section"),
                    rs.getDouble("percent"),
                    rs.getInt("submitNumber"),
                    rs.getString("submitYN"),
                    //rs.getString("question_id"),
                    new User(rs.getString("user_id"), null, null, null, sql)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quiz;
    }

    // 퀴즈 목록 조회
    public List<Quiz> findQuizList() {
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
                    rs.getString("section"),
                    rs.getDouble("percent"),
                    rs.getInt("submitNumber"),
                    rs.getString("submitYN"),
                    //rs.getString("question_id"),
                    new User(rs.getString("user_id"), null, null, null, sql)
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
        String sql = "UPDATE Quiz SET title = ?, section = ?, percent = ?, submitNumber = ?, submitYN = ? WHERE quiz_id = ?";

        try (Connection conn = connectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, quiz.getTitle());
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
    
    // 정답 확인
    public boolean checkAnswer(String quizId, String userAnswer) {
        String sql = "SELECT answer FROM Questions WHERE question_id = (SELECT question_id FROM Quiz WHERE quiz_id = ?)";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, quizId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("answer").equalsIgnoreCase(userAnswer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 퀴즈 상태 및 정답 비율 업데이트
    public int updateQuizStats(String quizId, boolean isCorrect) {
        String updateQuizSql = "UPDATE Quiz SET submitNumber = submitNumber + 1, " +
                               "percent = ((percent * (submitNumber - 1) + ?) / submitNumber), submitYN = 'Y' WHERE quiz_id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(updateQuizSql)) {

            pstmt.setDouble(1, isCorrect ? 1.0 : 0.0); // 정답이면 1.0 추가
            pstmt.setString(2, quizId);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public String findAnswerByQuizId(String quizId) {
        String sql = "SELECT answer FROM Questions WHERE quiz_id = ?";
        try (Connection conn = connectionManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, quizId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("answer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}