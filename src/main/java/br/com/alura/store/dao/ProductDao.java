package br.com.alura.store.dao;

import br.com.alura.store.model.Product;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProductDao {
    private final EntityManager em;

    public ProductDao(EntityManager em) {
        this.em = em;
    }

    public void create(Product product) {
        this.em.persist(product);
    }

    public Product getById(Long id) {
        return em.find(Product.class, id);
    }

    public List<Product> getAll() {
        String jpql = "SELECT p FROM Product p";
        return em.createQuery(jpql, Product.class).getResultList();
    }

    public List<Product> getByName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.name = :name";
        return em.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> getByCategoryName(String name) {
        String jpql = "SELECT p FROM Product p WHERE p.category.name = :name";
        return em.createQuery(jpql, Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public List<Product> getByCategoryNamedQuery(String name) {
        return em.createNamedQuery("Product.getByCategoryName", Product.class)
                .setParameter("name", name)
                .getResultList();
    }

    public BigDecimal getProductPriceByName(String name) {
        String jpql = "SELECT p.price FROM Product p WHERE p.name = :name";
        return em.createQuery(jpql, BigDecimal.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    public List<Product> findByParameters(String name, BigDecimal price, LocalDate createAt) {
        String jpql = "SELECT p FROM Product p WHERE 1=1";
        if (name != null && !jpql.isBlank())
            jpql += " AND  p.name = :name";
        if (price != null)
            jpql += " AND p.price = :price";
        if (createAt != null)
            jpql += " AND p.createAt = :createAt";

        TypedQuery<Product> query = em.createQuery(jpql, Product.class);
        if (name != null && !name.isBlank())
            query.setParameter("name", name);
        if (price != null)
            query.setParameter("price", price);
        if (createAt != null)
            query.setParameter("createAt", createAt);

        return query.getResultList();
    }

    public List<Product> findByParametersWithCriteria(String name, BigDecimal price, LocalDate createAt) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = builder.createQuery(Product.class);
        Root<Product> from = query.from(Product.class);
        Predicate filters = builder.and();

        if (name != null && !name.isBlank())
            filters = builder.and(filters, builder.equal(from.get("name"), name));
        if (price != null)
            filters = builder.and(filters, builder.equal(from.get("price"), price));
        if (createAt != null)
            filters = builder.and(filters, builder.equal(from.get("createAt"), createAt));

        query.where(filters);
        return em.createQuery(query).getResultList();
    }
}
