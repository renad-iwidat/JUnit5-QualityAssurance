package main.najah.code;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
class ProductTest {

    private Product product;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All: Setup complete.");

    }

    @AfterAll
    static void afterAll() {
        System.out.println("After All: Cleanup complete.");
    }

    @BeforeEach
    void setUp() {
        product = new Product("Test Product", 20.0);
        System.out.println("Before Each: Test setup complete.");
    }

    @AfterEach
    void tearDown() {
        System.out.println("After Each: Test teardown complete.");

    }

    @Test
    @DisplayName("Test valid product creation")
    @Order(1)
    void testValidProductCreation() {
        System.out.println("Running: Test valid product creation...");
        assertEquals("Test Product", product.getName(), "Product name should be 'Test Product'");
        assertEquals(20.0, product.getPrice(), "Product price should be 20.0");
        assertEquals(0, product.getDiscount(), "Product discount should be 0 initially");
    }

    // Test invalid price (negative price)
    @Test
    @DisplayName("Test invalid price")
    @Order(2)
    void testInvalidPrice() {
        System.out.println("Running: Test invalid price...");
        assertThrows(IllegalArgumentException.class, () -> new Product("Invalid Product", -10.0),
                "Price must be non-negative");
    }

    // Test applying a valid discount
    @Test
    @DisplayName("Test applying valid discount")
    @Order(3)
    void testApplyValidDiscount() {
        System.out.println("Running: Test applying valid discount...");
        product.applyDiscount(10.0); // Apply 10% discount
        assertEquals(18.0, product.getFinalPrice(), "Final price should be 18.0 after applying 10% discount");
    }

    // Test applying an invalid discount (greater than 50)
    @Test
    @DisplayName("Test applying discount greater than 50")
    @Order(4)
    void testInvalidDiscountGreaterThanFifty() {
        System.out.println("Running: Test applying discount greater than 50...");
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(60.0),
                "Discount must be between 0 and 50");
    }

    // Test applying an invalid discount (negative discount)
    @Test
    @DisplayName("Test applying negative discount")
    @Order(5)
    void testInvalidDiscountNegative() {
        System.out.println("Running: Test applying negative discount...");
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-10.0),
                "Discount must be between 0 and 50");
    }

    @ParameterizedTest
    @CsvSource({
            "0, 20.0",  // No discount, price should remain the same
            "10, 18.0", // 10% discount, price should be 18.0
            "50, 10.0", // 50% discount, price should be 10.0
    })
    @DisplayName("Test applying valid discounts with parameters")
    @Order(6)
    void testApplyDiscountParameterized(double discount, double expectedPrice) {
        System.out.println("Running: Test applying valid discounts with parameters...");
        product.applyDiscount(discount);
        assertEquals(expectedPrice, product.getFinalPrice(), 0.001, "Final price after discount should match expected");
    }

    // Test timeout to check that the execution completes within the specified time
    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Test timeout for product creation")
    @Order(7)
    void testTimeout() throws InterruptedException {
        System.out.println("Running: Test timeout for product creation...");
        Thread.sleep(50);
        assertEquals(20.0, product.getPrice(), "Price should still be 20.0");
    }

    @Test
    @Disabled("This test is intentionally failing and disabled.")
    @DisplayName("Intentionally failing test")
    @Order(8)
    void testIntentionallyFailing() {
        System.out.println("Running: Intentionally failing test...");
        fail("This is an intentionally failing test.");
    }
}
