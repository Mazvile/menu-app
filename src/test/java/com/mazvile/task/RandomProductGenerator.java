package com.mazvile.task;

import com.mazvile.task.model.Product;
import com.mazvile.task.model.Quantity;
import com.mazvile.task.model.Units;

import java.util.Random;

public class RandomProductGenerator {

    private String[] pcs = {"Potato", "Cucumber", "Paprika", "Eggplant", "Egg", "Apple", "Lemon"};
    private String[] grams = {"Salt", "Sugar", "Bacon", "Salmon", "Flour"};
    private String[] mililiters = {"Milk", "Sour cream", "Lemon juice", "Water"};

    public Product randomProduct() {
        Random rn = new Random();
        int randomNumber = rn.nextInt(3);
        Product product = new Product();
        Quantity quantity = new Quantity();
        switch (randomNumber) {
            case 0:
                randomNumber = rn.nextInt(pcs.length);
                product.setName(pcs[randomNumber]);
                randomNumber = rn.nextInt(5);
                quantity.setUnit(Units.PCS);
                quantity.setValue(randomNumber + 1);
                break;
            case 1:
                randomNumber = rn.nextInt(grams.length);
                product.setName(grams[randomNumber]);
                randomNumber = rn.nextInt(50);
                quantity.setUnit(Units.GRAMS);
                quantity.setValue((randomNumber + 1) * 10);
                break;
            case 2:
                randomNumber = rn.nextInt(mililiters.length);
                product.setName(mililiters[randomNumber]);
                randomNumber = rn.nextInt(5);
                quantity.setUnit(Units.MILLILITERS);
                quantity.setValue((randomNumber + 1) * 100);
                ;
                break;
        }
        product.setQuantity(quantity);
        return product;
    }
}
