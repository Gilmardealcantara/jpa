package br.com.alura.store;

import br.com.alura.store.dao.ProductDao;
import br.com.alura.store.model.Category;
import br.com.alura.store.model.Product;
import br.com.alura.store.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Product product = new Product("Xiome Redmin", "Top", new BigDecimal("800"), Category.CELLPHONES);

        EntityManager em = JPAUtil.getEntityManager();

        ProductDao productDao = new ProductDao(em);

        em.getTransaction().begin();
        productDao.create(product);
        em.getTransaction().commit();
        em.close();
    }
}
