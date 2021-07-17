package br.com.alura.store.dao;

import br.com.alura.store.model.Client;
import br.com.alura.store.model.Order;

import javax.persistence.EntityManager;

public class ClientDao {
    private final EntityManager em;

    public ClientDao(EntityManager em) {
        this.em = em;
    }

    public void create(Client client) {
        this.em.persist(client);
    }
}
