package com.mazvile.task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecipeBookReader {

    public RecipeBook readRecipesFromFile() {
        File file = new File("RecipeBook.txt");
        RecipeBook recipeBook = new RecipeBook();

        if (file.exists() && !file.isDirectory()) {
            BufferedReader buffreader = null;

            try {

                Reader reader = new FileReader(file);
                buffreader = new BufferedReader(reader);
                String lineFromFile = buffreader.readLine();

                while (lineFromFile != null && !lineFromFile.isEmpty()) {
                    recipeBook.addRecipe(recipeFromString(lineFromFile));
                    lineFromFile = buffreader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    buffreader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return recipeBook;
    }

    private Recipe recipeFromString(String line) {
        String[] parts = line.split(" ");
        String recipeName = parts[0];
        RecipeType recipeType = RecipeType.valueOf(parts[1]);
        List<Product> products = new ArrayList<>();
        for (int i = 2; i < parts.length; i = i + 3) {
            String productName = parts[i];
            int productValue = Integer.parseInt(parts[i + 1]);
            Units productUnits = Units.valueOf(parts[i + 2]);
            Product product = new Product(productName, productValue, productUnits);
            products.add(product);
        }
        Recipe recipe = new Recipe(recipeName, recipeType, products);
        return recipe;
    }
}
