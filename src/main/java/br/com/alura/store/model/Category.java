package br.com.alura.store.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "categories")
public class Category {
    @EmbeddedId
    private CategoryId id;

    public Category(String name) {
        this.id = new CategoryId(name, "xpto");
    }

    public Category() {

    }

    public String getName() {
        return this.id.getName();
    }

    public void setName(String name) {
        this.id.setName(name);
    }
}
