package fr.zsubhani;

import fr.zsubhani.quiz.DataModelObjectDAO.Choice;
import fr.zsubhani.quizService.ChoicesDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

import static org.mockito.Mockito.*;

class ChoicesDAOTest {
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ChoicesDAO choicesDAO;

    @BeforeEach
    void setUp() throws Exception {
        DataSource dataSource = mock(DataSource.class);
        connection = mock(Connection.class);
        preparedStatement = mock(PreparedStatement.class);

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        choicesDAO = new ChoicesDAO(dataSource);
    }

    @Test
    void testAdd() throws Exception {
        Choice choice = new Choice(1, 101, "Choice Text", true);

        choicesDAO.add(choice);

        verify(connection).prepareStatement("INSERT INTO choices (id, question_id, text, is_correct) VALUES (?, ?, ?, ?)");
        verify(preparedStatement).setInt(1, choice.getId());
        verify(preparedStatement).setInt(2, choice.getQuestionId());
        verify(preparedStatement).setString(3, choice.getText());
        verify(preparedStatement).setBoolean(4, choice.isCorrect());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testUpdate() throws Exception {
        Choice choice = new Choice(1, 102, "Updated Choice Text", false);

        choicesDAO.update(choice);

        verify(connection).prepareStatement("UPDATE choices SET question_id = ?, text = ?, is_correct = ? WHERE id = ?");
        verify(preparedStatement).setInt(1, choice.getQuestionId());
        verify(preparedStatement).setString(2, choice.getText());
        verify(preparedStatement).setBoolean(3, choice.isCorrect());
        verify(preparedStatement).setInt(4, choice.getId());
        verify(preparedStatement).executeUpdate();
    }

    @Test
    void testDelete() throws Exception {
        Choice choice = new Choice(1, 0, null, false);

        choicesDAO.delete(choice);

        verify(connection).prepareStatement("DELETE FROM choices WHERE id = ?");
        verify(preparedStatement).setInt(1, choice.getId());
        verify(preparedStatement).executeUpdate();
    }
}
