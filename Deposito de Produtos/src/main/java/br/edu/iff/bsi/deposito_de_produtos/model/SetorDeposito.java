package br.edu.iff.bsi.deposito_de_produtos.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SetorDeposito implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String descricao;
    @Column(nullable = true)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Produto> produtos = new ArrayList<>();

    public void trim(){
        this.descricao = this.descricao.trim();
    }
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
        this.descricao = descricao.trim();
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    public void addProdutos(Produto produto){
        this.produtos.add(produto);
    }
    public void removerProduto(Produto produto){
        this.produtos.remove(produto);
    }

}
