package com.mazvile.task;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public Menu makeRandomMenu(
            int numberOfFishDishes,
            int numberOfMeatDishes,
            int numberOfChickenDishes,
            int numberOfVeggieDishes) {

        List<Recipe> selectedFishDishes = getRandomDishes(RecipeType.FISH, numberOfFishDishes);
        List<Recipe> selectedMeatDishes = getRandomDishes(RecipeType.MEAT, numberOfMeatDishes);
        List<Recipe> selectedChickenDishes = getRandomDishes(RecipeType.CHICKEN, numberOfChickenDishes);
        List<Recipe> selectedVeggieDishes = getRandomDishes(RecipeType.VEGETARIAN, numberOfVeggieDishes);

        List<Recipe> allMenuRecipes = new ArrayList<>();
        allMenuRecipes.addAll(selectedFishDishes);
        allMenuRecipes.addAll(selectedMeatDishes);
        allMenuRecipes.addAll(selectedChickenDishes);
        allMenuRecipes.addAll(selectedVeggieDishes);

        Menu menu = new Menu(allMenuRecipes);

        return menu;
    }

    private List<Recipe> getRandomDishes(RecipeType recipeType, int numberOfDishes) {
        List<Recipe> allDishes = getRecipeByType(recipeType);
        Random rn = new Random();
        List<Recipe> selectedDishes = new ArrayList<>();
        for (int i = 0; i < numberOfDishes; i++) {
            int randomIndex = rn.nextInt(allDishes.size());
            selectedDishes.add(allDishes.remove(randomIndex));
        }
        return selectedDishes;
    }


    public List<Recipe> getRecipesFromProductsFromSupplies(Supplies supplies) {

        return null;
    }
}
