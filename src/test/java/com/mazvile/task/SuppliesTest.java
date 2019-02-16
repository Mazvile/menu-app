package com.mazvile.task;

import com.mazvile.task.logic.Supplies;
import com.mazvile.task.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SuppliesTest {

    @Test
    void canIMakeThisRecipeShouldReturnTrueIfThereAreEnoughSupplies() {
        List<Product> products = new ArrayList<>();
        Product sugar = new Product("Sugar", 1000, Units.GRAMS);
        Product moreSugar = new Product("Sugar", 2000, Units.GRAMS);
        products.add(sugar);
        Recipe recipe = new Recipe("Testcipe", RecipeType.DESSERT, products);
        Supplies supplies = new Supplies();
        supplies.addProduct(moreSugar);
        assertTrue(supplies.canIMakeThisRecipe(recipe));
    }

    @Test
    void canIMakeThisRecipeShouldReturnFalseIfThereAreNotEnoughSupplies() {
        List<Product> products = new ArrayList<>();
        Product sugar = new Product("Sugar", 1000, Units.GRAMS);
        Product lessSugar = new Product("Sugar", 500, Units.GRAMS);
        products.add(sugar);
        Recipe recipe = new Recipe("Testcipe", RecipeType.DESSERT, products);
        Supplies supplies = new Supplies();
        supplies.addProduct(lessSugar);
        assertFalse(supplies.canIMakeThisRecipe(recipe));
    }

    @Test
    void productsToBuyShouldReturnEmptyListIfThereAreEnoughProducts() {
        List<Product> products1 = new ArrayList<>();
        List<Product> products2 = new ArrayList<>();
        List<Recipe> recipes = new ArrayList<>();
        Supplies supplies = new Supplies();
        Product recipeProduct1 = new Product("P1", 2, Units.PCS);
        Product recipeProduct2 = new Product("P2", 100, Units.GRAMS);
        Product recipeProduct3 = new Product("P1", 5, Units.PCS);
        Product suppliesProduct1 = new Product("P1", 10, Units.PCS);
        Product suppliesProduct2 = new Product("P2", 100, Units.GRAMS);
        products1.add(recipeProduct1);
        products1.add(recipeProduct2);
        products2.add(recipeProduct3);
        supplies.addProduct(suppliesProduct1);
        supplies.addProduct(suppliesProduct2);
        Recipe recipe1 = new Recipe("R1", RecipeType.DESSERT, products1);
        Recipe recipe2 = new Recipe("R2", RecipeType.FISH, products2);
        recipes.add(recipe1);
        recipes.add(recipe2);
        Menu menu = new Menu(recipes);
        assertTrue(supplies.productsToBuy(menu).isEmpty());
    }
}
