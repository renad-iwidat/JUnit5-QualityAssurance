package main.najah.code;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.util.concurrent.TimeUnit;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // To order test execution
class ProductTest {

    private Product product;

    // @BeforeEach hook to set up before each test
    @BeforeEach
    void setUp() {
        product = new Product("Test Product", 20.0);
        System.out.println("setup complete");
    }

    // @AfterEach hook to clean up after each test
    @AfterEach
    void tearDown() {
        System.out.println("test complete");
    }

    // Test valid product creation
    @Test
    @DisplayName("Test valid product creation")
    @Order(1)
    void testValidProductCreation() {
        assertEquals("Test Product", product.getName(), "Product name should be 'Test Product'");
        assertEquals(20.0, product.getPrice(), "Product price should be 20.0");
        assertEquals(0, product.getDiscount(), "Product discount should be 0 initially");
    }

    // Test invalid price (negative price)
    @Test
    @DisplayName("Test invalid price")
    @Order(2)
    void testInvalidPrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Invalid Product", -10.0),
                "Price must be non-negative");
    }

    // Test applying a valid discount
    @Test
    @DisplayName("Test applying valid discount")
    @Order(3)
    void testApplyValidDiscount() {
        product.applyDiscount(10.0); // Apply 10% discount
        assertEquals(18.0, product.getFinalPrice(), "Final price should be 18.0 after applying 10% discount");
    }

    // Test applying an invalid discount (greater than 50)
    @Test
    @DisplayName("Test applying discount greater than 50")
    @Order(4)
    void testInvalidDiscountGreaterThanFifty() {
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(60.0),
                "Discount must be between 0 and 50");
    }

    // Test applying an invalid discount (negative discount)
    @Test
    @DisplayName("Test applying negative discount")
    @Order(5)
    void testInvalidDiscountNegative() {
        assertThrows(IllegalArgumentException.class, () -> product.applyDiscount(-10.0),
                "Discount must be between 0 and 50");
    }

    // Parameterized test for different discounts
    @ParameterizedTest
    @CsvSource({
            "0, 20.0",  // No discount, price should remain the same
            "10, 18.0", // 10% discount, price should be 18.0
            "50, 10.0", // 50% discount, price should be 10.0
    })
    @DisplayName("Test applying valid discounts with parameters")
    @Order(6)
    void testApplyDiscountParameterized(double discount, double expectedPrice) {
        product.applyDiscount(discount);
        assertEquals(expectedPrice, product.getFinalPrice(), 0.001, "Final price after discount should match expected");
    }

    // Test timeout to check that the execution completes within the specified time
    @Test
    @Timeout(value = 100, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Test timeout for product creation")
    @Order(7)
    void testTimeout() throws InterruptedException {
        // Simulate a short delay
        Thread.sleep(50); // Simulate a small delay
        assertEquals(20.0, product.getPrice(), "Price should still be 20.0");
    }

    // Intentionally failing test (will be ignored using @Disabled)
    @Test
    @Disabled("This test is intentionally failing and disabled.")
    @DisplayName("Intentionally failing test")
    @Order(8)
    void testIntentionallyFailing() {
        fail("This is an intentionally failing test.");
    }
}
