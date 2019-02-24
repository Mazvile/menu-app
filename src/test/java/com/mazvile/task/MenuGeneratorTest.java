package com.mazvile.task;

import com.mazvile.task.logic.MenuGenerator;
import com.mazvile.task.logic.RecipeBook;
import com.mazvile.task.model.Menu;
import com.mazvile.task.model.Recipe;
import com.mazvile.task.model.RecipeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MenuGeneratorTest {
    private RecipeBook rb = new RecipeBook();
    private MenuGenerator mg = new MenuGenerator(rb);

    @BeforeEach
    void setUpTestEnvironment() {
        rb.addRecipe("Chicken", RecipeType.POULTRY, new ArrayList<>());
        rb.addRecipe("Fish", RecipeType.FISH, new ArrayList<>());
        rb.addRecipe("Meat", RecipeType.MEAT, new ArrayList<>());
        rb.addRecipe("Veggie1", RecipeType.VEGETARIAN, new ArrayList<>());
        rb.addRecipe("Veggie2", RecipeType.VEGETARIAN, new ArrayList<>());
    }

    @Test
    void makeRandomMenuShouldReturnEmptyMenuIfAllDishesAreZero() {
        Menu menu = mg.makeRandomMenu(0, 0, 0, 0);
        assertEquals(0, menu.getMenuRecipes().size());
    }

    @Test
    void makeRandomMenuShouldReturnMenuWithFiveDishesIfFiveDishesWereRequestedInTotal() {
        Menu menu = mg.makeRandomMenu(0, 0, 5, 0);
        assertEquals(5, menu.getMenuRecipes().size());
    }

    @Test
    void makeRandomMenuShouldReturnMenuWithCorrectNumberOfDishesByType() {
        Menu menu = mg.makeRandomMenu(1, 2, 3, 4);
        assertEquals(10, menu.getMenuRecipes().size());
        int poultry = 0;
        int fish = 0;
        int meat = 0;
        int veggie = 0;
        for (Recipe recipe : menu.getMenuRecipes()) {
            switch (recipe.getDishType()) {
                case POULTRY:
                    poultry++;
                    break;
                case FISH:
                    fish++;
                    break;
                case MEAT:
                    meat++;
                    break;
                case VEGETARIAN:
                    veggie++;
                    break;
            }
        }
        assertEquals(1, fish);
        assertEquals(2, meat);
        assertEquals(3, poultry);
        assertEquals(4, veggie);
    }

    @Test
    void makeRandomMenuShouldTreatNegativeInputNumbersAsZeroes() {
        Menu menu = mg.makeRandomMenu(-1, -3, -4, 3);
        assertEquals(3, menu.getMenuRecipes().size());
    }

    @Test
    void makeRandomMenuShouldTwoDifferentDishesIfTwoDishesAreInRecipeBook() {
        Menu menu = mg.makeRandomMenu(0, 0, 0, 2);
        assertNotEquals(menu.getMenuRecipes().get(0).getName(), menu.getMenuRecipes().get(1).getName());
    }
}
