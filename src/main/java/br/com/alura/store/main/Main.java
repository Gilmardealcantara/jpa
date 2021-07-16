package br.com.alura.store.main;

import br.com.alura.store.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Product product = new Product();
        product.setName("Xiome Redmin");
        product.setDescription("Top");
        product.setPrice(new BigDecimal("800"));

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("store");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }
}
