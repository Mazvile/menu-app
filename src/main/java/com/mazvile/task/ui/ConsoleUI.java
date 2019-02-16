package com.mazvile.task.ui;

import com.mazvile.task.logic.MenuGenerator;
import com.mazvile.task.model.Recipe;
import com.mazvile.task.logic.RecipeBook;
import com.mazvile.task.logic.Supplies;
import com.mazvile.task.io.WriterToFile;
import com.mazvile.task.model.Menu;
import com.mazvile.task.model.Product;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI implements UI{

    private Supplies supplies;
    private RecipeBook recipeBook;
    private Scanner in = new Scanner(System.in);
    private MenuGenerator menuGenerator;
    private WriterToFile rw = new WriterToFile();

    public ConsoleUI(Supplies supplies, RecipeBook recipeBook, MenuGenerator menuGenerator) {
        this.supplies = supplies;
        this.recipeBook = recipeBook;
        this.menuGenerator = menuGenerator;
    }

    @Override
    public void start() {
        System.out.println("Hello, it's a menu making app");
        System.out.println("Your options are listed below, please type the number to choose form them");
        System.out.println();
        options();
    }

    private void options() {
        System.out.println("If you want to make menu from recipes, press 1");
        System.out.println("If you want to make random menu, press 2");
        System.out.println("If you want to check what you can make from your supplies, press 3");
        System.out.println();
        int choice = readNextInt();
        switch (choice) {
            case 1:
                chooseFromRecipeBook();
                break;
            case 2:
                makeRandomMenu();
                break;
            case 3:
                showAvailableRecipes();
                break;
            default:
                System.out.println("Bad choice, repeat, please.");
                options();
        }
    }

    private void productListPrinting(List<Product> products) {
        System.out.println("Need to buy:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println("" + (i + 1) + ". " + products.get(i).getName()
                    + " " + products.get(i).getQuantity().getValue()
                    + " " + products.get(i).getQuantity().getUnit());
        }
    }

    private void recipeListPrinting(Menu newMenu) {
        System.out.println("Weeks menu:");
        int counter = 1;
        for (Recipe rp : newMenu.getMenuRecipes()) {
            System.out.println("" + counter + ". " + rp.getName());
            counter++;
        }
    }

    private void recipeListPrinting(List<Recipe> recipes) {
        int counter = 1;
        for (Recipe rp : recipes) {
            System.out.println("" + counter + ". " + rp.getName());
            counter++;
        }
    }

    private void printOutReminder(List<Recipe> recipes, List<Product> products) {
        System.out.println("Printing your Reminder.txt . . . .");
        rw.writeReminder(recipes, products);
        System.out.println("Your reminder is printed");
    }

    private void chooseFromRecipeBook() {
        System.out.println("Select dishes by pressing number of the dish and when you're done - press 0");
        recipeListPrinting(recipeBook.getRecipes());
        List<Recipe> chosenRecipes = new ArrayList<>();

        int numberOfDish = readNextInt();

        while (numberOfDish != 0) {
            if (numberOfDish > 0 && numberOfDish < recipeBook.getRecipes().size()) {
                chosenRecipes.add(recipeBook.getRecipes().get(numberOfDish - 1));
                numberOfDish = readNextInt();
            } else {
                System.out.println("Invalid number, please, try again.");
                numberOfDish = readNextInt();
            }
        }
        System.out.println("Your choices are:");
        recipeListPrinting(chosenRecipes);
        System.out.println();
        Menu menu = new Menu(chosenRecipes);
        productListPrinting(supplies.productsToBuy(menu));
        System.out.println();
        printOutReminder(chosenRecipes, supplies.productsToBuy(menu));
    }

    private void makeRandomMenu() {
        System.out.println("How many fish dishes you would like to eat this week?");
        int numberOfFishDishes = readNextInt();
        System.out.println("How many poultry dishes you would like to eat this week?");
        int numberOfPoultryDishes = readNextInt();
        System.out.println("How many meat dishes you would like to eat this week?");
        int numberOfMeatDishes = readNextInt();
        System.out.println("How many vegetarian dishes you would like to eat this week?");
        int numberOfVeggieDishes = readNextInt();
        Menu newMenu = menuGenerator.makeRandomMenu(numberOfFishDishes, numberOfMeatDishes, numberOfPoultryDishes, numberOfVeggieDishes); //standard menu
        System.out.println("You can make this recipes:");
        recipeListPrinting(newMenu);
        System.out.println();
        productListPrinting(supplies.productsToBuy(newMenu));
        System.out.println();
        printOutReminder(newMenu.getMenuRecipes(), supplies.productsToBuy(newMenu));
    }

    private void showAvailableRecipes() {
        List<Recipe> recipesFromSupplies = menuGenerator.getRecipesFromProductsFromSupplies(supplies);
        System.out.println("You can make:");
        recipeListPrinting(recipesFromSupplies);
    }

    private int readNextInt() {
        int choice = -1;
        while (choice < 0) {
            try {
                choice = in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid number, please, try again");
                in.next();
            }
        }
        return choice;
    }
}
