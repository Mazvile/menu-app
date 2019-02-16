package com.mazvile.task;

import com.mazvile.task.io.IReader;
import com.mazvile.task.io.ReaderFromFile;
import com.mazvile.task.logic.MenuGenerator;
import com.mazvile.task.logic.RecipeBook;
import com.mazvile.task.logic.Supplies;
import com.mazvile.task.ui.ConsoleUI;
import com.mazvile.task.ui.UI;

public class Main {
    public static void main(String[] args) {
        IReader reader = new ReaderFromFile();
        RecipeBook testBook = reader.readRecipes();
        Supplies testSupplies = reader.readSupplies();
        MenuGenerator testGenerator = new MenuGenerator(testBook);
        UI consoleUi = new ConsoleUI(testSupplies, testBook, testGenerator);
        consoleUi.start();
    }
}
