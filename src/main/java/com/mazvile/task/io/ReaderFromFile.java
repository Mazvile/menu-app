package com.mazvile.task.io;

import com.mazvile.task.logic.RecipeBook;
import com.mazvile.task.logic.Supplies;
import com.mazvile.task.model.Product;
import com.mazvile.task.model.Recipe;
import com.mazvile.task.model.RecipeType;
import com.mazvile.task.model.Units;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderFromFile implements IReader {

    @Override
    public RecipeBook readRecipes() {
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
        return new Recipe(recipeName, recipeType, products);
    }

    @Override
    public Supplies readSupplies() {
        File file = new File("Supplies.txt");
        Supplies supplies = new Supplies();

        if (file.exists() && !file.isDirectory()) {
            BufferedReader buffreader = null;
            try {
                Reader reader = new FileReader(file);
                buffreader = new BufferedReader(reader);
                String lineFromFile = buffreader.readLine();

                while (lineFromFile != null && !lineFromFile.isEmpty()) {
                    supplies.addProduct(productFromString(lineFromFile));
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
        return supplies;
    }

    private Product productFromString(String line) {
        String[] parts = line.split(" ");
        String productName = parts[0];
        int productValue = Integer.parseInt(parts[1]);
        Units productUnits = Units.valueOf(parts[2]);
        return new Product(productName, productValue, productUnits);
    }
}
