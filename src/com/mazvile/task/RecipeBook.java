package com.mazvile.task;

import java.util.ArrayList;
import java.util.List;

public class RecipeBook {

    private List<Recipe> recipes = new ArrayList<Recipe>();

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public List<Recipe> getRecipeByType(RecipeType type) {

        List<Recipe> filteredRecipes = new ArrayList<Recipe>();
        for (Recipe recipe : recipes) {
            if (recipe.getDishType().equals(type)) {
                filteredRecipes.add(recipe);
            }
        }
        return filteredRecipes;
    }

    public void addRecipe(String name, RecipeType type, List<Product> products) {
        Recipe recipe = new Recipe(name, type, products);
        recipes.add(recipe);
    }

}
