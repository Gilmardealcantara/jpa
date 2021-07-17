package br.com.alura.store.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_itens")
public class OrderItem {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    private int qnt;
    @ManyToOne
    private Order order;
    @ManyToOne
    private Product product;

    public OrderItem(int qnt, Order order, Product product) {
        this.qnt = qnt;
        this.order = order;
        this.product = product;
        this.unitPrice = product.getPrice();
    }

    public OrderItem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public int getQnt() {
        return qnt;
    }

    public void setQnt(int qnt) {
        this.qnt = qnt;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
