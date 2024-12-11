package fr.zsubhani.quiz.DataModelObjectDAO;



public class Choice {
    private int id;
    private int questionId;
    private String text;
    private boolean isCorrect;

    // Constructor
    public Choice(int id, int questionId, String text, boolean isCorrect) {
        this.id = id;
        this.questionId = questionId;
        this.text = text;
        this.isCorrect = isCorrect;
    }

    // Default Constructor
    public Choice() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    @Override
    public String toString() {
        return "Choice{" +
                "id=" + id +
                ", questionId=" + questionId +
                ", text='" + text + '\'' +
                ", isCorrect=" + isCorrect +
                '}';
    }
}
