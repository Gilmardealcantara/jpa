package br.com.alura.store.dao;

import br.com.alura.store.model.Product;

import javax.persistence.EntityManager;

public class ProductDao {
    private final EntityManager em;

    public ProductDao(EntityManager em) {
        this.em = em;
    }

    public void create(Product product){
        this.em.persist(product);
    }
}
