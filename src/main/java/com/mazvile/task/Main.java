package com.mazvile.task;

import com.mazvile.task.io.RecipeBookReader;
import com.mazvile.task.io.SuppliesReader;
import com.mazvile.task.logic.MenuGenerator;
import com.mazvile.task.logic.RecipeBook;
import com.mazvile.task.logic.Supplies;
import com.mazvile.task.ui.UI;

public class Main {

    public static void main(String[] args) {
        RecipeBookReader recipeBookReader = new RecipeBookReader();
        SuppliesReader suppliesReader = new SuppliesReader();
        RecipeBook testBook = recipeBookReader.readRecipesFromFile();
        Supplies testSupplies = suppliesReader.readSuppliesFromFile();
        MenuGenerator testGenerator = new MenuGenerator(testBook);

        UI ui = new UI(testSupplies, testBook, testGenerator);
        ui.hello();
    }


}
