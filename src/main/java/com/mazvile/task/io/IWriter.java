package com.mazvile.task.io;

import com.mazvile.task.model.Product;
import com.mazvile.task.model.Recipe;

import java.util.List;

public interface IWriter {
    void writeReminder(List<Recipe> recipes, List<Product> products);
}
