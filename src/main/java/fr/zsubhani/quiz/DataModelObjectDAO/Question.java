package fr.zsubhani.quiz.DataModelObjectDAO;

public class Question {
    private int id; // Primary key
    public String question; // Question text

    // Constructors
    public Question() {}

    public Question(int id, String question) {
        this.id = id;
        this.question = question;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                '}';
    }
}
