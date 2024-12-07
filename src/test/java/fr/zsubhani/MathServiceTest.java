package fr.zsubhani;
import fr.zsubhani.service.MathService;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class MathServiceTest {
    @Test
    public void firstTest() {
        int testNumber = 5;
        long expectedFactorial = 120; // 5! = 120

        // Call the factorial method
        long result = MathService.factorial(testNumber);

        // Assert the result
        assertEquals(expectedFactorial, result, "Factorial calculation failed");
    }
}
