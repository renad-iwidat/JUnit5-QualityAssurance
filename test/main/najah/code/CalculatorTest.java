package main.najah.code;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator Test")
class CalculatorTest {

    Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Test
    @DisplayName("Addition: Valid and Negative Numbers")
    void add() {
        assertAll("Valid addition tests",
                () -> assertEquals(6, calculator.add(1, 2, 3), "1+2+3 should be 6"),
                () -> assertEquals(10, calculator.add(2, 3, 5), "2+3+5 should be 10"),
                () -> assertEquals(0, calculator.add(0, 0, 0), "0+0+0 should be 0"),
                () -> assertEquals(-6, calculator.add(-1, -2, -3), "-1+-2+-3 should be -6"),
                () -> assertEquals(4, calculator.add(4), "Single number should return itself"),
                () -> assertEquals(0, calculator.add(), "No input should return 0")
        );
    }

    @ParameterizedTest
    @CsvSource({
            "1, 2, 3",
            "2, 3, 5",
            "-1, -2, -3",
            "10, 20, 30",
            "0, 0, 0"
    })
    @DisplayName("Parameterized Addition Test")
    void testAddParameterized(int a, int b, int expected) {
        assertEquals(expected, calculator.add(a, b), () -> "Expected sum of " + a + " and " + b + " to be " + expected);
    }

    @Test
    @DisplayName("Division: Valid Cases and Divide by Zero")
    void divide() {
        assertAll("Valid division tests",
                () -> assertEquals(2, calculator.divide(6, 3), "6 / 3 should be 2"),
                () -> assertEquals(5, calculator.divide(10, 2), "10 / 2 should be 5"),
                () -> assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0), "Cannot divide by zero")
        );
    }

    @Test
    @DisplayName("Factorial: Valid Cases")
    void factorialValid() {
        assertAll("Valid factorial tests",
                () -> assertEquals(120, calculator.factorial(5), "5! should be 120"),
                () -> assertEquals(1, calculator.factorial(0), "0! should be 1"),
                () -> assertEquals(1, calculator.factorial(1), "1! should be 1")
        );
    }

    @Test
    @DisplayName("Factorial: Negative Input Should Throw Exception")
    void factorialInvalid() {
        assertThrows(IllegalArgumentException.class, () -> calculator.factorial(-5), "Negative input should throw IllegalArgumentException");
    }

    @Test
    @DisplayName("Factorial Calculation Should Complete Within 1 Second")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testFactorialTimeout() {
        assertDoesNotThrow(() -> calculator.factorial(15), "Factorial should execute within 1 second");
    }
}
