package com.mazvile.task.model;

import com.mazvile.task.model.Product;
import com.mazvile.task.model.RecipeType;

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

    public void setDishType(RecipeType dishType) {
        this.dishType = dishType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
