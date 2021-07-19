package br.com.alura.store.model;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Computing extends Product {
     private String brand;
     private String model;

    public Computing(String name, String description, BigDecimal price, Category category, String brand, String model) {
        super(name, description, price, category);
        this.brand = brand;
        this.model = model;
    }

    public Computing() {
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
