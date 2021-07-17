package br.com.alura.store.dao;

import br.com.alura.store.model.Order;
import br.com.alura.store.model.Product;

import javax.persistence.EntityManager;

public class OrderDao {
    private final EntityManager em;

    public OrderDao(EntityManager em) {
        this.em = em;
    }

    public void create(Order order) {
        this.em.persist(order);
    }
}
