package nora;

import java.sql.Timestamp;
import java.util.List;

public class Question {
    private int id;
    private int userId;
    private String title;
    private String question;
    private Timestamp createdAt;
    private List<Answer> answers;

    public Question(int id, int userId, String title, String question,Timestamp createdAt) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.question = question;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return this.title;
    }
    
}