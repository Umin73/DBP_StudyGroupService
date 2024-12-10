package model.domain;

import java.util.List;
import java.util.UUID;

public class Quiz {
    private String quizId;       // 퀴즈 ID
    private String title;        // 퀴즈 제목
    private String groupId;      // 그룹 ID
    private String section;      // 섹션 정보
    private double percent;      // 정답률
    private int submitNumber;    // 제출자 수
    private String submitYN;     // 제출 여부 (Y/N)
    //private String questionId;   // 질문 ID
    private User createdBy;      // 퀴즈 생성자
    //private List<Question> questions;  // 퀴즈에 포함된 질문 리스트

    // 생성자
    public Quiz(String quizId, String title, String groupId, String section, double percent,
                int submitNumber, String submitYN, User createdBy) {
        this.quizId = quizId;
        this.title = title;
        this.groupId = (groupId != null) ? groupId : "3d085fa1c83a41b9a19cda7a41cb19d7"; // 기본 그룹 ID
        this.section = section;
        this.percent = percent;
        this.submitNumber = submitNumber;
        this.submitYN = submitYN;
        //this.questionId = (getQuestionId() != null) ? questionId : null;
        this.createdBy = (createdBy != null) ? createdBy : new User("test1", null, "name1", null, null);
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

    /*
     * public String getQuestionId() { return questionId; }
     */

    public User getCreatedBy() {
        return createdBy;
    }

    /*
     * public List<Question> getQuestions() { return questions; }
     */

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public void setSubmitNumber(int submitNumber) {
        this.submitNumber = submitNumber;
    }

    public void setSubmitYN(String submitYN) {
        this.submitYN = submitYN;
    }

    /*
     * public void setQuestionId(String questionId) { this.questionId = questionId;
     * }
     */

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    /*
     * public void setQuestions(List<Question> questions) { this.questions =
     * questions; }
     */

    public void createQuiz() {
        // 퀴즈 생성 로직
        
        this.quizId = UUID.randomUUID().toString(); // 고유 ID 생성
        this.percent = 0.0; // 초기 정답률 설정
        this.submitNumber = 0; // 초기 제출자 수 설정
        this.submitYN = "N"; // 초기 제출 여부 설정
    }

    public void attemptQuiz(User user) {
        // 퀴즈 시도 로직
        
        if (this.submitYN.equals("Y")) {
            System.out.println(user.getUserId() + "님은 이미 이 퀴즈를 시도했습니다.");
            return;
        }

        // 유저가 퀴즈를 시도했다고 가정
        this.submitNumber++; // 제출자 수 증가
        this.submitYN = "Y"; // 유저의 제출 상태 업데이트
    }

    public boolean checkAnswer(String userAnswer, String correctAnswer) {
        // 답안 확인 로직
        
        return userAnswer != null && userAnswer.equalsIgnoreCase(correctAnswer);
    }

    public void updateSubmitStatus(boolean isCorrect) {
        // 제출 상태 업데이트
        
        this.submitNumber++;
        if (isCorrect) {
            this.percent = ((this.percent * (this.submitNumber - 1)) + 1.0) / this.submitNumber;
        } else {
            this.percent = (this.percent * (this.submitNumber - 1)) / this.submitNumber;
        }
        this.submitYN = "Y";
    }

}