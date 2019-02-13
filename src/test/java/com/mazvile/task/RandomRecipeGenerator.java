package com.mazvile.task;

import com.mazvile.task.model.Product;
import com.mazvile.task.model.Recipe;
import com.mazvile.task.model.RecipeType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class RandomRecipeGenerator {

    public Recipe makeRandomRecipe() {
        Recipe recipe = new Recipe(setRandomName(), setRandomType(), setRandomProducts());
        return recipe;
    }

    private String setRandomName() {
        String uniqueName = "Recipe Nr. " + (UUID.randomUUID().toString());
        return uniqueName;
    }

    private RecipeType setRandomType() {
        Random rn = new Random();
        int type = rn.nextInt(6);
        switch (type) {
            case 0:
                return RecipeType.POULTRY;
            case 1:
                return RecipeType.FISH;
            case 2:
                return RecipeType.VEGETARIAN;
            case 3:
                return RecipeType.MEAT;
            case 4:
                return RecipeType.SOUP;
            default:
                return RecipeType.DESSERT;
        }
    }

    private List<Product> setRandomProducts() {
        RandomProductGenerator randomProductGenerator = new RandomProductGenerator();
        List<Product> randomProducts = new ArrayList<>();
        Random rn = new Random();
        int numberOfProducts = rn.nextInt(5) + 3;
        for (int i = 0; i < numberOfProducts; i++) {
            randomProducts.add(randomProductGenerator.randomProduct());
        }
        return randomProducts;
    }
}
