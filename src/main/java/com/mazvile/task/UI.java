package com.mazvile.task;

import java.util.List;
import java.util.Scanner;

public class UI {

    Supplies supplies;
    RecipeBook recipeBook;
    Scanner in = new Scanner(System.in);
    MenuGenerator menuGenerator;

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
                System.out.println("Not implemented yet");
                break;
            case 2:
                Menu newMenu = menuGenerator.makeRandomMenu(0, 0, 0, 1); //standard menu
                System.out.println("Random menu made");
                productListPrinting(supplies.productsToBuy(newMenu));
                break;
            case 3:
                //menuGenerator.getRecipesFromProductsFromSupplies(supplies);
                System.out.println("You can make this recipes:");
                break;
        }
    }

    public void productListPrinting(List<Product> products) {
        System.out.println("Need to buy:");
        System.out.println();
        for (int i = 0; i < products.size(); i++) {
            System.out.println("" + (i + 1) + ". " + products.get(i).getName()
                    + " " + products.get(i).getQuantity().getValue()
                    + " " + products.get(i).getQuantity().getUnit());
        }

    }

    public void recipeListPrinting(Menu newMenu) {
        System.out.println("Weeks menu:");
        for (Recipe rp : newMenu.getMenuRecipes()) {
            System.out.println(rp.getName());
        }
    }

}
