package com.mazvile.task;

import com.mazvile.task.logic.RecipeBook;
import com.mazvile.task.model.Recipe;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mazvile.task.model.RecipeType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class RecipeBookTest {

    @Test
    void getRecipeByTypeShouldReturnOnlyRecipesOfTheTypeGiven() {
        RecipeBook rb = new RecipeBook();
        rb.addRecipe("Fish dish", FISH, new ArrayList<>());
        rb.addRecipe("Salmon dish", FISH, new ArrayList<>());
        rb.addRecipe("Meat dish", MEAT, new ArrayList<>());
        rb.addRecipe("Dessert", DESSERT, new ArrayList<>());

        List<Recipe> filteredRecipes = rb.getRecipeByType(FISH);

        assertEquals(2, filteredRecipes.size());
        assertEquals("Fish dish", filteredRecipes.get(0).getName());
        assertEquals("Salmon dish", filteredRecipes.get(1).getName());
    }

    @Test
    void addRecipeShouldDoAddRecipeToRecipeBook() {
        RecipeBook rb = new RecipeBook();
        assertEquals(0, rb.getRecipes().size());
        rb.addRecipe("Fish dish", FISH, new ArrayList<>());
        assertEquals(1, rb.getRecipes().size());
    }

    @Test
    void addRecipeShouldHaveName() {
        RecipeBook rb = new RecipeBook();
        boolean ifAdded = rb.addRecipe(" ", DESSERT, new ArrayList<>());
        assertFalse(ifAdded);
        assertEquals(0, rb.getRecipes().size());
    }

    @Test
    void addRecipeOverloadedShouldDoAddRecipeToRecipeBook() {
        RecipeBook rb = new RecipeBook();
        Recipe testRecipe = new Recipe("Fish dish", FISH, new ArrayList<>());
        assertEquals(0, rb.getRecipes().size());
        rb.addRecipe(testRecipe);
        assertEquals(1, rb.getRecipes().size());
    }

    @Test
    void addRecipeOverloadedShouldHaveName() {
        RecipeBook rb = new RecipeBook();
        Recipe testRecipe = new Recipe(" ", DESSERT, new ArrayList<>());
        boolean ifAdded = rb.addRecipe(testRecipe);
        assertFalse(ifAdded);
        assertEquals(0, rb.getRecipes().size());
    }
}
