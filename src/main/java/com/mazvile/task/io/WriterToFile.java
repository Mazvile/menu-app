package com.mazvile.task.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mazvile.task.model.Product;
import com.mazvile.task.model.Recipe;
import org.apache.commons.io.FileUtils;

public class WriterToFile implements IWriter {

    @Override
    public void writeReminder(List<Recipe> recipes, List<Product> products) {
        try {
            File file = new File("Reminder.txt");

            List<String> lines = new ArrayList<>();
            lines.add("Week's menu:");
            for (int i = 0; i < recipes.size(); i++) {
                lines.add("" + (i + 1) + ". " + recipes.get(i).getName());
            }
            lines.add("");
            lines.add("Products to buy:");
            for (int i = 0; i < products.size(); i++) {
                lines.add("" + (i + 1) + ". " + products.get(i).getName() + " "
                        + products.get(i).getQuantity().getValue() + " " + products.get(i).getQuantity().getUnit());
            }

            FileUtils.writeLines(file, "UTF-8", lines, false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
