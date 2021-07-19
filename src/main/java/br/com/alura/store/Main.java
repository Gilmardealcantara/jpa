package br.com.alura.store;

import br.com.alura.store.dao.CategoryDao;
import br.com.alura.store.dao.ClientDao;
import br.com.alura.store.dao.OrderDao;
import br.com.alura.store.dao.ProductDao;
import br.com.alura.store.model.*;
import br.com.alura.store.utils.JPAUtil;

import javax.persistence.EntityManager;
import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {

        testProductDynamicFilters();
    }

    private static void testProductDynamicFilters() {
        createProduct();
        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDao = new ProductDao(em);
        productDao.findByParameters("Xiome Redmin", null, null);
        productDao.findByParameters("Xiome Redmin", new BigDecimal("800"), null);
        productDao.findByParameters(null, new BigDecimal("800"), null);
        productDao.findByParametersWithCriteria("Xiome Redmin", new BigDecimal("800"), null);
        productDao.findByParametersWithCriteria(null, new BigDecimal("800"), null);
        productDao.findByParametersWithCriteria("Xiome Redmin", null, null);
    }

    private static void testQueriesPerformance() {
        createOrder();
        EntityManager em = JPAUtil.getEntityManager();
        OrderDao orderDao = new OrderDao(em);
        Order order = em.find(Order.class, 1L);
        System.out.println(order.getItems().size());
        order.getItems().forEach(x -> System.out.println(x.getProduct()));
        Order orderWithClient = orderDao.getWithClient(1L);
        System.out.println(orderWithClient.getClient().getName());
    }

    private static void testOrderQueries() {
        createOrder();
        EntityManager em = JPAUtil.getEntityManager();
        OrderDao orderDao = new OrderDao(em);

        System.out.println("TOTAL VALUE: " + orderDao.soldTotalValue());

        orderDao.salesReport().forEach(x -> {
            System.out.println(x.getProductName());
            System.out.println(x.getQntSold());
            System.out.println(x.getDateLastSold());
        });

        orderDao.salesReportObject().forEach(x -> {
            System.out.println(x[0]);
            System.out.println(x[1]);
            System.out.println(x[2]);
        });
    }

    private static void createOrder() {
        createProduct();
        EntityManager em = JPAUtil.getEntityManager();
        ProductDao productDao = new ProductDao(em);
        Product product = productDao.getById(1L);

        em.getTransaction().begin();
        ClientDao clientDao = new ClientDao(em);
        Client client = new Client("Gilmar", "1234");
        clientDao.create(client);

        OrderDao orderDao = new OrderDao(em);
        Order order = new Order(client);
        order.addItem(new OrderItem(10, order, product));

        orderDao.create(order);

        em.getTransaction().commit();
        em.close();
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
        productDao.getByCategoryNamedQuery("cellphone").forEach(v -> System.out.println(v.getName()));
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
