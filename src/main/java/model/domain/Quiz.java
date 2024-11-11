package model.domain;

import java.util.List;

public class Quiz {
    private String quizId;       // 퀴즈 ID
    private String title;        // 퀴즈 제목
    private String groupId;      // 그룹 ID
    private double score;        // 퀴즈 점수
    private String section;      // 섹션 정보
    private double percent;      // 정답률
    private int submitNumber;    // 제출자 수
    private String submitYN;     // 제출 여부 (Y/N)
    private String questionId;   // 질문 ID
    private User createdBy;      // 퀴즈 생성자
    private List<Question> questions;  // 퀴즈에 포함된 질문 리스트

    // 생성자
    public Quiz(String quizId, String title, String groupId, double score, String section, double percent,
                int submitNumber, String submitYN, String questionId, User createdBy) {
        this.quizId = quizId;
        this.title = title;
        this.groupId = groupId;
        this.score = score;
        this.section = section;
        this.percent = percent;
        this.submitNumber = submitNumber;
        this.submitYN = submitYN;
        this.questionId = questionId;
        this.createdBy = createdBy;
    }

    // Getter 메서드들
    public String getQuizId() {
        return quizId;
    }

    public String getTitle() {
        return title;
    }

    public String getGroupId() {
        return groupId;
    }

    public double getScore() {
        return score;
    }

    public String getSection() {
        return section;
    }

    public double getPercent() {
        return percent;
    }

    public int getSubmitNumber() {
        return submitNumber;
    }

    public String getSubmitYN() {
        return submitYN;
    }

    public String getQuestionId() {
        return questionId;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    // 기존 메서드들
    public void createQuiz() {
        // 퀴즈 생성 로직
    }

    public void attemptQuiz(User user) {
        // 퀴즈 시도 로직
    }

    public void viewAnswer(User user) {
        // 답안 확인 로직
    }
}