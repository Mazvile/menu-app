package com.mazvile.task.model;

public class Product {

    private String name;
    private Quantity quantity;

    public Product() {

    }

    public Product(String name, int value, Units units) {
        this.name = name;
        this.quantity = new Quantity();
        quantity.setValue(value);
        quantity.setUnit(units);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
}
