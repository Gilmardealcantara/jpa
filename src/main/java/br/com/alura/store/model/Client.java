package br.com.alura.store.model;

import javax.persistence.*;

@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PersonalData personalData;

    public Client(String name, String cpf) {
        personalData = new PersonalData(name, cpf);
    }

    public Client() {
    }

    public String getName() {
        return personalData.getName();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
