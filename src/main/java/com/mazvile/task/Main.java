package com.mazvile.task;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        //testCase();
        testCase2();
    }

    private static void testCase() {
        RecipeBook testBook = new RecipeBook();
        Supplies testSupplies = new Supplies();
        List<Product> testProductsForRecipe = new ArrayList<>();
        MenuGenerator testGenerator = new MenuGenerator(testBook);

        Product testProduct1 = new Product("Paprika", 2, Units.PCS);
        Product testProduct2 = new Product("Flour", 200, Units.GRAMS);
        Product testProduct3 = new Product("Salt", 2, Units.GRAMS);
        Product testProduct4 = new Product("Milk", 500, Units.MILILITERS);
        Product testProduct5 = new Product("Pea", 1, Units.PCS);
        Product testProduct6 = new Product("Milk", 50, Units.MILILITERS);
        Product testProduct7 = new Product("Paprika", 3, Units.PCS);

        testProductsForRecipe.add(testProduct5);
        testProductsForRecipe.add(testProduct6);
        testProductsForRecipe.add(testProduct7);

        testSupplies.addProduct(testProduct1);
        testSupplies.addProduct(testProduct2);
        testSupplies.addProduct(testProduct3);
        testSupplies.addProduct(testProduct4);

        testBook.addRecipe("Food", RecipeType.VEGETARIAN, testProductsForRecipe);
        testBook.addRecipe("Another Food", RecipeType.VEGETARIAN, testProductsForRecipe);
        testBook.addRecipe("Meat Food", RecipeType.MEAT, testProductsForRecipe);
        Menu testMenu = testGenerator.makeRandomMenu(0, 1, 0, 2);


        System.out.println("Weeks menu:");
        for (Recipe rp : testMenu.getMenuRecipes()) {
            System.out.println(rp.getName());
        }

        System.out.println("Need to buy:");
        List<Product> toBuy = testSupplies.productsToBuy(testMenu);

        for (Product pr: toBuy){
            System.out.println(pr.getName() + " " + pr.getQuantity().getValue() + " " + pr.getQuantity().getUnit());
        }
    }

    private static void testCase2() {
        RecipeBook testBook = new RecipeBook();
        Supplies testSupplies = new Supplies();
        MenuGenerator testGenerator = new MenuGenerator(testBook);
        ProductMaker fakeProduct = new ProductMaker();
        RecipeMaker fakeRecipe = new RecipeMaker();

        for (int i = 0; i < 10; i ++) {
            testSupplies.addProduct(fakeProduct.randomProduct());
        }

        for (int i = 0; i < 30; i++) {
            testBook.getRecipes().add(fakeRecipe.makeRandomRecipe());
        }

        Menu fakeMenu = testGenerator.makeRandomMenu(1, 2, 3, 2);

        System.out.println("Weeks menu:");
        for (Recipe rp : fakeMenu.getMenuRecipes()) {
            System.out.println(rp.getName());
        }

        System.out.println("Need to buy:");
        List<Product> toBuy = testSupplies.productsToBuy(fakeMenu);

        for (Product pr: toBuy){
            System.out.println(pr.getName() + " " + pr.getQuantity().getValue() + " " + pr.getQuantity().getUnit());
        }
    }
}
