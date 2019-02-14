package com.mazvile.task.io;

import com.mazvile.task.logic.RecipeBook;
import com.mazvile.task.logic.Supplies;

public interface IReader {
    RecipeBook readRecipes();
    Supplies readSupplies();
}
