package com.mazvile.task.logic;

import com.mazvile.task.model.Product;
import com.mazvile.task.model.Recipe;
import com.mazvile.task.model.RecipeType;
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
        if (!StringUtils.isBlank(recipe.getName())) {
            recipes.add(recipe);
            return true;
        }
        return false;
    }
}
