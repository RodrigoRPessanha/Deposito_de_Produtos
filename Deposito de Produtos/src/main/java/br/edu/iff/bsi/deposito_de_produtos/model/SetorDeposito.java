package br.edu.iff.bsi.deposito_de_produtos.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class SetorDeposito {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = true)
    @OneToMany(mappedBy = "setor", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Produto> produtos = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Deposito deposito;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public Deposito getDeposito() {
        return deposito;
    }

    public void setDeposito(Deposito deposito) {
        this.deposito = deposito;
    }
}
