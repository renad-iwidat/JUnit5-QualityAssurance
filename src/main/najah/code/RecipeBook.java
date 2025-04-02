package main.najah.code;

public class RecipeBook {

    /** Array of recipes in coffee maker */
    private Recipe[] recipeArray;
    /** Number of recipes in coffee maker */
    private final int NUM_RECIPES = 4;

    /**
     * Default constructor for a RecipeBook.
     */
    public RecipeBook() {
        recipeArray = new Recipe[NUM_RECIPES];
    }

    /**
     * Returns the recipe array.
     * @return Recipe[]
     */
    public synchronized Recipe[] getRecipes() {
        return recipeArray;
    }

    /**
     * Adds a new recipe if there's space and it's not a duplicate.
     * @param r The recipe to add.
     * @return true if added, false otherwise.
     */
    public synchronized boolean addRecipe(Recipe r) {
        boolean exists = false;

        for (int i = 0; i < recipeArray.length; i++) {
            if (recipeArray[i] != null && r.equals(recipeArray[i])) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            for (int i = 0; i < recipeArray.length; i++) {
                if (recipeArray[i] == null) {
                    recipeArray[i] = r;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Deletes a recipe at the specified index.
     * @param index Index of the recipe to delete.
     * @return Name of deleted recipe, or null if invalid index.
     */
    public synchronized String deleteRecipe(int index) {
        if (index < 0 || index >= recipeArray.length || recipeArray[index] == null) {
            return null;
        }
        String deletedRecipeName = recipeArray[index].getName();
        recipeArray[index] = null;
        return deletedRecipeName;
    }

    /**
     * Edits a recipe at the specified index.
     * @param index Index of the recipe to edit.
     * @param newRecipe New recipe object.
     * @return Name of old recipe, or null if invalid index.
     */
    public synchronized String editRecipe(int index, Recipe newRecipe) {
        if (index < 0 || index >= recipeArray.length || recipeArray[index] == null) {
            return null;
        }
        String oldRecipeName = recipeArray[index].getName();
        recipeArray[index] = newRecipe;
        return oldRecipeName;
    }
}
