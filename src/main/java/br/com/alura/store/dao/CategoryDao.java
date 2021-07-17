package br.com.alura.store.dao;

import br.com.alura.store.model.Category;

import javax.persistence.EntityManager;

public class CategoryDao {
    private final EntityManager em;

    public CategoryDao(EntityManager em) {
        this.em = em;
    }

    public void create(Category category){
        this.em.persist(category);
    }
}
