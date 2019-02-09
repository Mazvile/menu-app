package com.mazvile.task;

import java.util.List;

public class Menu {

    private List<Recipe> menuRecipes;

    public Menu(List<Recipe> recipes) {
        this.menuRecipes = recipes;
    }

    public List<Recipe> getMenuRecipes() {
        return menuRecipes;
    }
}
