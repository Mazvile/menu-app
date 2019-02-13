package com.mazvile.task.ui;

import com.mazvile.task.logic.MenuGenerator;
import com.mazvile.task.model.Recipe;
import com.mazvile.task.logic.RecipeBook;
import com.mazvile.task.logic.Supplies;
import com.mazvile.task.io.ReminderWriter;
import com.mazvile.task.model.Menu;
import com.mazvile.task.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UI {

    private Supplies supplies;
    private RecipeBook recipeBook;
    private Scanner in = new Scanner(System.in);
    private MenuGenerator menuGenerator;
    private ReminderWriter rw = new ReminderWriter();

    public UI(Supplies supplies, RecipeBook recipeBook, MenuGenerator menuGenerator) {
        this.supplies = supplies;
        this.recipeBook = recipeBook;
        this.menuGenerator = menuGenerator;
    }

    public void hello() {
        System.out.println("Hello, it's a menu making app");
        System.out.println();
        System.out.println("Your options are listed below, please type the number to choose form them");
        System.out.println();
        options();
    }

    public void options() {
        System.out.println("If you want to make menu from recipes, press 1");
        System.out.println("If you want to make random menu, press 2");
        System.out.println("If you want to check what you can make from your supplies, press 3");
        System.out.println();
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Select dishes by pressing number of the dish and when you're done - press 0");
                recipeListPrinting(recipeBook.getRecipes());
                List<Recipe> chosenRecipes = new ArrayList<>();
                int numberOfDish = in.nextInt();
                while (numberOfDish != 0) {
                    chosenRecipes.add(recipeBook.getRecipes().get(numberOfDish - 1));
                    numberOfDish = in.nextInt();
                }
                System.out.println("Your choices are:");
                recipeListPrinting(chosenRecipes);
                System.out.println();
                Menu menu = new Menu(chosenRecipes);
                productListPrinting(supplies.productsToBuy(menu));
                System.out.println();
                System.out.println("Printing your Reminder.txt");

                rw.writeReminderToFile(chosenRecipes, supplies.productsToBuy(menu));
                break;
            case 2:
                System.out.println("How many fish dishes you would like to eat this week?");
                int numberOfFishDishes = in.nextInt();
                System.out.println("How many poultry dishes you would like to eat this week?");
                int numberOfPoultryDishes = in.nextInt();
                System.out.println("How many meat dishes you would like to eat this week?");
                int numberOfMeatDishes = in.nextInt();
                System.out.println("How many vegetarian dishes you would like to eat this week?");
                int numberOfVeggieDishes = in.nextInt();
                Menu newMenu = menuGenerator.makeRandomMenu(numberOfFishDishes, numberOfMeatDishes, numberOfPoultryDishes, numberOfVeggieDishes); //standard menu
                recipeListPrinting(newMenu);
                System.out.println();
                productListPrinting(supplies.productsToBuy(newMenu));
                break;
            case 3:
                List<Recipe> recipesFromSupplies = menuGenerator.getRecipesFromProductsFromSupplies(supplies);
                recipeListPrinting(recipesFromSupplies);
                break;
        }
    }

    public void productListPrinting(List<Product> products) {
        System.out.println("Need to buy:");
        for (int i = 0; i < products.size(); i++) {
            System.out.println("" + (i + 1) + ". " + products.get(i).getName()
                    + " " + products.get(i).getQuantity().getValue()
                    + " " + products.get(i).getQuantity().getUnit());
        }

    }

    public void recipeListPrinting(Menu newMenu) {
        System.out.println("Weeks menu:");
        int counter = 1;
        for (Recipe rp : newMenu.getMenuRecipes()) {
            System.out.println("" + counter + ". " + rp.getName());
            counter++;
        }
    }

    public void recipeListPrinting(List<Recipe> recipes) {
        System.out.println("You can make this recipes:");
        int counter = 1;
        for (Recipe rp : recipes) {
            System.out.println("" + counter + ". " + rp.getName());
            counter++;
        }
    }

    public void printOutReminder() {

    }

}
