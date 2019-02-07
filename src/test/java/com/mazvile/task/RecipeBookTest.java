package com.mazvile.task;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.mazvile.task.RecipeType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class RecipeBookTest {

    @Test
    public void getRecipeByTypeShouldReturnOnlyRecipesOfTheTypeGiven() {
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
    public void addRecipeShouldDoAddRecipeToRecipeBook() {
        RecipeBook rb = new RecipeBook();
        assertEquals(0, rb.getRecipes().size());
        rb.addRecipe("Fish dish", FISH, new ArrayList<>());
        assertEquals(1, rb.getRecipes().size());
    }

    @Test
    public void addRecipeShouldHaveNameAdded() {
        RecipeBook rb = new RecipeBook();
        boolean ifAdded = rb.addRecipe(" ", DESSERT, new ArrayList<>());
        assertFalse(ifAdded);
        assertEquals(0, rb.getRecipes().size());

    }

}
