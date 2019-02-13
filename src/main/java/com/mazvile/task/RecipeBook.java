package com.mazvile.task;

import org.apache.commons.lang3.StringUtils;

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

    public boolean addRecipe(String name, RecipeType type, List<Product> products) {
        if (!StringUtils.isBlank(name)) {
            Recipe recipe = new Recipe(name, type, products);
            recipes.add(recipe);
            return true;
        }
        return false;
    }

    public boolean addRecipe(Recipe recipe) {
        return recipes.add(recipe);
    }
}
