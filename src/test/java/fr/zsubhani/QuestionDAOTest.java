package fr.zsubhani;


import fr.zsubhani.quiz.DataModelObjectDAO.Question;
import fr.zsubhani.quizService.QuestionDAO;

import java.sql.SQLException;
import java.util.List;

public class QuestionDAOTest {

    public static void main(String[] args) {
        QuestionDAO questionDAO = new QuestionDAO();

        try {
            // Step 1: Ensure the table is created
            questionDAO.createTableIfNotExists();

            // Step 2: Add questions
            Question q1 = new Question(0, "What is Java?");
            Question q2 = new Question(0, "What is H2 Database?");
            questionDAO.addQuestion(q1);
            questionDAO.addQuestion(q2);

            // Step 3: Fetch all questions
            List<Question> allQuestions = questionDAO.getAllQuestions();
            System.out.println("All Questions:");
            allQuestions.forEach(System.out::println);

            // Step 4: Fetch a specific question by ID
            Question fetchedQuestion = questionDAO.getQuestionById(1);
            System.out.println("Fetched Question by ID:");
            System.out.println(fetchedQuestion);

            // Step 5: Update a question
            fetchedQuestion.setQuestion("What is JDBC?");
            questionDAO.updateQuestion(fetchedQuestion);
            System.out.println("Updated Question:");
            System.out.println(questionDAO.getQuestionById(1));

            // Step 6: Delete a question
            questionDAO.deleteQuestion(2);
            System.out.println("After Deletion:");
            questionDAO.getAllQuestions().forEach(System.out::println);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

