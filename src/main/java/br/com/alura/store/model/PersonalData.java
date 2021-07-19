package br.com.alura.store.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class PersonalData {
    private String name;
    private String cpf;

    public PersonalData(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
    }

    public PersonalData() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
