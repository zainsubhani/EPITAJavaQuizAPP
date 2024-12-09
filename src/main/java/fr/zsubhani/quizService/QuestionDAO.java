package fr.zsubhani.quizService;


import fr.zsubhani.quiz.DataModelObjectDAO.Question;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class QuestionDAO {

    public void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS questions (" +
                "id INT PRIMARY KEY AUTO_INCREMENT, " +
                "question VARCHAR(255))";

        try (Connection connection = Configuration.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute();
        }
    }

    public void addQuestion(Question question) throws SQLException {
        String sql = "INSERT INTO questions (question) VALUES (?)";

        try (Connection connection = Configuration.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, question.getQuestion());
            statement.executeUpdate();
        }
    }

    public List<Question> getAllQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        String sql = "SELECT * FROM questions";

        try (Connection connection = Configuration.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getInt("id"));
                question.setQuestion(resultSet.getString("question"));
                questions.add(question);
            }
        }

        return questions;
    }

    public Question getQuestionById(int id) throws SQLException {
        String sql = "SELECT * FROM questions WHERE id = ?";
        Question question = null;

        try (Connection connection = Configuration.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    question = new Question();
                    question.setId(resultSet.getInt("id"));
                    question.setQuestion(resultSet.getString("question"));
                }
            }
        }

        return question;
    }

    public void updateQuestion(Question question) throws SQLException {
        String sql = "UPDATE questions SET question = ? WHERE id = ?";

        try (Connection connection = Configuration.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, question.getQuestion());
            statement.setInt(2, question.getId());
            statement.executeUpdate();
        }
    }

    public void deleteQuestion(int id) throws SQLException {
        String sql = "DELETE FROM questions WHERE id = ?";

        try (Connection connection = Configuration.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}
