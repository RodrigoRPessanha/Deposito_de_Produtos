package br.edu.iff.bsi.deposito_de_produtos.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Deposito implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;


    @Column(nullable = false)
    private String descricao;

    @Column(nullable = true)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<SetorDeposito> setores = new ArrayList<>();

    public Deposito() {
    }

    public Deposito(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public void trim(){
        this.descricao = this.descricao.trim();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<SetorDeposito> getSetores() {
        return setores;
    }

    public void addSetores(SetorDeposito setores) {
        this.setores.add(setores);
    }

    public void removeSetor(SetorDeposito setores) {
        this.setores.remove(setores);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao.trim();
    }
}
