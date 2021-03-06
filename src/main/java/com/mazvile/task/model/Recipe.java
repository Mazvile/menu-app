package com.mazvile.task.model;

import java.util.List;

public class Recipe {

    private String name;
    private RecipeType dishType;
    private List<Product> products;

    public Recipe(String name, RecipeType type, List<Product> products) {
        this.name = name;
        this.dishType = type;
        this.products = products;
    }

    public RecipeType getDishType() {
        return dishType;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }
}
