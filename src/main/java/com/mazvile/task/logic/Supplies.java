package com.mazvile.task.logic;

import com.mazvile.task.model.Menu;
import com.mazvile.task.model.Product;
import com.mazvile.task.model.Recipe;

import java.util.ArrayList;
import java.util.List;

public class Supplies {

    private List<Product> supplies = new ArrayList<>();

    public void addProduct(Product product) {
        this.supplies.add(product);
    }

    public boolean canIMakeThisRecipe(Recipe recipe) {
        List<Product> requiredProducts = recipe.getProducts();
        int counter = 0;
        for (Product product : requiredProducts) {
            for (Product supply : supplies) {
                if ((product.getName().equals(supply.getName())) && (product.getQuantity().getValue() <= supply.getQuantity().getValue())) {
                    counter++;
                }
            }
        }
        return counter == requiredProducts.size();
    }

    public List<Product> productsToBuy(Menu menu) {
        List<Recipe> menuRecipes = menu.getMenuRecipes();
        List<Product> productsNeeded = new ArrayList<>();
        for (Recipe recipe : menuRecipes) {
            for (Product product : recipe.getProducts()) {
                Product copy = new Product(product.getName(), product.getQuantity().getValue(), product.getQuantity().getUnit());
                productsNeeded.add(copy);
            }
        }
        List<Product> optimizedProducts = sumSameProducts(productsNeeded);
        return subtractSupplies(optimizedProducts);
    }

    private List<Product> subtractSupplies(List<Product> productsNeeded) {
        List<Product> result = new ArrayList<>();
        for (Product product : productsNeeded) {
            for (Product supply : supplies) {
                if (product.getName().equals(supply.getName())) {
                    int val1 = product.getQuantity().getValue();
                    int val2 = supply.getQuantity().getValue();

                    product.getQuantity().setValue(val1 - val2);
                }
            }
            if (product.getQuantity().getValue() > 0) {
                result.add(product);
            }
        }
        return result;
    }

    private List<Product> sumSameProducts(List<Product> products) {
        List<Product> optimizedProductList = new ArrayList<>();
        while (!products.isEmpty()) {
            Product productTaken = products.remove(0);
            boolean alreadyInList = false;
            for (Product optProduct : optimizedProductList) {
                if (productTaken.getName().equals(optProduct.getName())) {
                    int val1 = optProduct.getQuantity().getValue();
                    int val2 = productTaken.getQuantity().getValue();
                    optProduct.getQuantity().setValue(val1 + val2);
                    alreadyInList = true;
                }
            }
            if (!alreadyInList) {
                optimizedProductList.add(productTaken);
            }
        }
        return optimizedProductList;
    }
}
