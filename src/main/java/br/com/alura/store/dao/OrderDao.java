package br.com.alura.store.dao;

import br.com.alura.store.model.Order;
import br.com.alura.store.model.Product;
import br.com.alura.store.model.vo.SalesReportVo;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    public List<SalesReportVo> salesReport() {
        String jpql = "SELECT new br.com.alura.store.model.vo.SalesReportVo(product.name, SUM(item.qnt), MAX(o.date)) FROM Order o "
                + "JOIN o.items item "
                + "JOIN item.product product "
                + "GROUP BY product.name "
                + "ORDER BY item.qnt DESC";

        return em.createQuery(jpql, SalesReportVo.class)
                .getResultList();
    }

    public List<Object[]> salesReportObject() {
        String jpql = "SELECT product.name, SUM(item.qnt), MAX(o.date) FROM Order o "
                + "JOIN o.items item "
                + "JOIN item.product product "
                + "GROUP BY product.name "
                + "ORDER BY item.qnt DESC";

        return em.createQuery(jpql, Object[].class)
                .getResultList();
    }

    public Order getWithClient(Long id) {
        return em.createQuery("SELECT o FROM Order o JOIN FETCH o.client WHERE o.id = :id", Order.class)
                .setParameter("id", id)
                .getSingleResult();
    }
}
