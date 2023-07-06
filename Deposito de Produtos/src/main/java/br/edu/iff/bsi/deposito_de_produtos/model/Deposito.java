package br.edu.iff.bsi.deposito_de_produtos.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Deposito {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @OneToMany(mappedBy = "deposito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SetorDeposito> setores = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SetorDeposito> getSetores() {
        return setores;
    }

    public void setSetores(List<SetorDeposito> setores) {
        this.setores = setores;
    }
}
