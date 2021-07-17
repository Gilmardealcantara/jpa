package br.com.alura.store;

import br.com.alura.store.dao.CategoryDao;
import br.com.alura.store.dao.ProductDao;
import br.com.alura.store.model.Category;
import br.com.alura.store.model.Product;
import br.com.alura.store.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        testQueries();
    }

    private static void testQueries() {
        createProduct();
        Long id = 1L;

        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDao = new ProductDao(em);

        Product p = productDao.getById(id);
        System.out.println(p.getPrice());

        productDao.getAll().forEach(v -> System.out.println(v.getName()));
        productDao.getByName("Xiome Redmin").forEach(v -> System.out.println(v.getCategory().getName()));
        productDao.getByCategoryName("cellphone").forEach(v -> System.out.println(v.getName()));
        System.out.println("product price: " + productDao.getProductPriceByName("Xiome Redmin"));

    }

    private static void testEntityState() {
        EntityManager em = JPAUtil.getEntityManager();

        Category cellphones = new Category("cellphone");
        // cellphones is transient
        cellphones.setName("other"); // do notting in db

        em.getTransaction().begin();
        em.persist(cellphones); // insert in db
        // cellphones is managed now
        cellphones.setName("other 2"); // update db

        em.flush();
        em.clear();
        // cellphones is managed detached now
        cellphones.setName("other 3"); // do notting in db

        Category managedCellphone = em.merge(cellphones);
        managedCellphone.setName("other 4");
        em.flush();
        em.remove(cellphones); // Error Removing a detached instance
        em.remove(managedCellphone);
        // removed
        em.getTransaction().commit();
        em.close();
    }

    private static void createProduct() {
        EntityManager em = JPAUtil.getEntityManager();

        CategoryDao categoryDao = new CategoryDao(em);
        ProductDao productDao = new ProductDao(em);

        Category cellphones = new Category("cellphone");
        Product product = new Product("Xiome Redmin", "Top", new BigDecimal("800"), cellphones);

        em.getTransaction().begin();
        categoryDao.create(cellphones);
        productDao.create(product);
        em.getTransaction().commit();
        em.close();
    }
}
