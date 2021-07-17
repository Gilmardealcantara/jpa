package br.com.alura.store.dao;

import br.com.alura.store.model.Order;
import br.com.alura.store.model.Product;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class OrderDao {
    private final EntityManager em;

    public OrderDao(EntityManager em) {
        this.em = em;
    }

    public void create(Order order) {
        this.em.persist(order);
    }

    public BigDecimal soldTotalValue() {
        String jpql = "SELECT SUM(o.totalValue) FROM Order o";
        return em.createQuery(jpql, BigDecimal.class)
                .getSingleResult();
    }
}
