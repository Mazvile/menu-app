package com.mazvile.task.io;

import com.mazvile.task.model.Product;
import com.mazvile.task.logic.Supplies;
import com.mazvile.task.model.Units;

import java.io.*;

public class SuppliesReader {


    public Supplies readSuppliesFromFile() {
        File file = new File("Supplies.txt");
        Supplies supplies = new Supplies();

        if (file.exists() && !file.isDirectory()) {
            BufferedReader buffreader = null;

            try {

                Reader reader = new FileReader(file);
                buffreader = new BufferedReader(reader);
                String lineFromFile = buffreader.readLine();

                while (lineFromFile != null && !lineFromFile.isEmpty()) {
                    supplies.addProduct(productFromString(lineFromFile));
                    lineFromFile = buffreader.readLine();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    buffreader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } else {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return supplies;
    }

    private Product productFromString(String line) {
        String[] parts = line.split(" ");
        String productName = parts[0];
        int productValue = Integer.parseInt(parts[1]);
        Units productUnits = Units.valueOf(parts[2]);
        Product product = new Product(productName, productValue, productUnits);
        return product;
    }
}
