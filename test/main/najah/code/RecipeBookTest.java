package main.najah.code;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

@DisplayName("Tests for RecipeBook class")
public class RecipeBookTest {

    private RecipeBook recipeBook;

    @BeforeEach
    void setUp() {
        recipeBook = new RecipeBook();
    }

    @Test
    @DisplayName("Test adding a valid recipe")
    void testAddValidRecipe() throws RecipeException {
        Recipe recipe = createRecipe("Coffee", "5", "2", "3", "1", "1");

        boolean result = recipeBook.addRecipe(recipe);
        assertTrue(result, "Recipe should be added successfully.");
    }

    @Test
    @DisplayName("Test adding an invalid recipe with negative price")
    void testAddInvalidRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Invalid Coffee");

        assertThrows(RecipeException.class, () -> recipe.setPrice("-5"),
                "Price must be a positive integer");
    }

    @Test
    @DisplayName("Test deleting an existing recipe")
    void testDeleteExistingRecipe() throws RecipeException {
        Recipe recipe = createRecipe("Cappuccino", "4", "1", "2", "2", "1");

        recipeBook.addRecipe(recipe);

        String deletedRecipe = recipeBook.deleteRecipe(0);
        assertNotNull(deletedRecipe, "Deleted recipe name should not be null.");
        assertEquals("Cappuccino", deletedRecipe, "The deleted recipe should be Cappuccino.");
    }

    @Test
    @DisplayName("Test trying to delete a non-existing recipe")
    void testDeleteNonExistingRecipe() {
        String deletedRecipe = recipeBook.deleteRecipe(10);
        assertNull(deletedRecipe, "Deleting a non-existing recipe should return null.");
    }

    @Test
    @DisplayName("Test editing an existing recipe")
    void testEditExistingRecipe() throws RecipeException {
        Recipe recipe = createRecipe("Latte", "6", "2", "3", "2", "1");
        recipeBook.addRecipe(recipe);

        Recipe newRecipe = createRecipe("Iced Latte", "7", "3", "4", "2", "2");

        String oldRecipeName = recipeBook.editRecipe(0, newRecipe);
        assertNotNull(oldRecipeName, "Editing should return the old recipe name.");
        assertEquals("Latte", oldRecipeName, "The old recipe name should be Latte.");
    }

    @Test
    @DisplayName("Test editing a non-existing recipe")
    void testEditNonExistingRecipe() {
        Recipe newRecipe = new Recipe();
        newRecipe.setName("Non-Existing Coffee");

        String oldRecipeName = recipeBook.editRecipe(10, newRecipe);
        assertNull(oldRecipeName, "Editing a non-existing recipe should return null.");
    }

    @Test
    @DisplayName("Test timeout on long-running addRecipe operation")
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
    void testAddRecipeTimeout() throws RecipeException {
        Recipe recipe = createRecipe("Quick Coffee", "4", "1", "2", "1", "1");
        recipeBook.addRecipe(recipe);
    }

    @ParameterizedTest
    @DisplayName("Test adding valid recipes with different prices")
    @CsvSource({"5", "10", "20"})
    void testAddRecipeWithValidPrice(String price) throws RecipeException {
        Recipe recipe = createRecipe("Valid Coffee", price, "1", "2", "1", "1");

        boolean result = recipeBook.addRecipe(recipe);
        assertTrue(result, "Recipe with price " + price + " should be added successfully.");
    }

    @Test
    @DisplayName("Test getting all recipes")
    void testGetRecipes() throws RecipeException {
        Recipe recipe1 = createRecipe("Espresso", "3", "1", "2", "0", "1");
        Recipe recipe2 = createRecipe("Mocha", "5", "2", "3", "1", "2");

        recipeBook.addRecipe(recipe1);
        recipeBook.addRecipe(recipe2);

        Recipe[] recipes = recipeBook.getRecipes();
        assertNotNull(recipes, "Recipe array should not be null.");
        assertEquals(4, recipes.length, "Recipe book should have space for 4 recipes.");
        assertEquals("Espresso", recipes[0].getName(), "First recipe should be Espresso.");
        assertEquals("Mocha", recipes[1].getName(), "Second recipe should be Mocha.");
    }

    @AfterEach
    void tearDown() {
    }

    private Recipe createRecipe(String name, String price, String chocolate, String coffee, String milk, String sugar) throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        recipe.setPrice(price);
        recipe.setAmtChocolate(chocolate);
        recipe.setAmtCoffee(coffee);
        recipe.setAmtMilk(milk);
        recipe.setAmtSugar(sugar);
        return recipe;
    }
}
