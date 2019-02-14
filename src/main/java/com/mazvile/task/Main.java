package com.mazvile.task;

import com.mazvile.task.io.IReader;
import com.mazvile.task.io.ReaderFromFile;
import com.mazvile.task.logic.MenuGenerator;
import com.mazvile.task.logic.RecipeBook;
import com.mazvile.task.logic.Supplies;
import com.mazvile.task.ui.UI;

public class Main {

    public static void main(String[] args) {
        IReader recipeBookReader = new ReaderFromFile();
        IReader suppliesReader = new ReaderFromFile();
        RecipeBook testBook = recipeBookReader.readRecipes();
        Supplies testSupplies = suppliesReader.readSupplies();
        MenuGenerator testGenerator = new MenuGenerator(testBook);

        UI ui = new UI(testSupplies, testBook, testGenerator);
        ui.hello();
    }
}
