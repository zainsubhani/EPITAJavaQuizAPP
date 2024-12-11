package fr.zsubhani.quizService;

import fr.zsubhani.quiz.DataModelObjectDAO.Choice;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ChoicesDAO {
    private final DataSource dataSource;

    public ChoicesDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void add(Choice choice) {
        String sql = "INSERT INTO choices (id, question_id, text, is_correct) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, choice.getId());
            statement.setInt(2, choice.getQuestionId());
            statement.setString(3, choice.getText());
            statement.setBoolean(4, choice.isCorrect());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Choice choice) {
        String sql = "UPDATE choices SET question_id = ?, text = ?, is_correct = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, choice.getQuestionId());
            statement.setString(2, choice.getText());
            statement.setBoolean(3, choice.isCorrect());
            statement.setInt(4, choice.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Choice choice) {
        String sql = "DELETE FROM choices WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, choice.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
