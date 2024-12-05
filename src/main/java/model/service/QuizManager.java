package model.service;

import java.sql.SQLException;
import java.util.List;

import model.dao.QuizDAO;
import model.domain.Quiz;

public class QuizManager {
    private static QuizManager quizMan = new QuizManager();
    private QuizDAO quizDao;
    
    private QuizManager() {
        try {
            quizDao = new QuizDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static QuizManager getInstance() {
        return quizMan;
    }
    
    public int create(Quiz quiz) throws SQLException, ExistingGroupException {

        return quizDao.createQuiz(quiz);
    }
    
    public List<Quiz> findAllQuizzes() throws SQLException {
        return quizDao.findQuizList();
    }
}