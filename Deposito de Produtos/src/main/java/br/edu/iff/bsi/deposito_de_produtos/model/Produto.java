package br.edu.iff.bsi.deposito_de_produtos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Descrição obrigatória.")
    @Column(nullable = false)
    private String descricao;
    @NotBlank(message = "Codigo de barras obrigatório.")
    @Column(nullable = false)
    private String codigoDeBarras;
    @Positive
    @Column(nullable = false)
    private int quantidade;
    @Positive
    @Column(nullable = false)
    private BigDecimal precoCusto;

    public void trim(){
        this.descricao = this.descricao.trim();
        this.codigoDeBarras = this.codigoDeBarras.trim();
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
        this.descricao = descricao;
    }

    public String getCodigoDeBarras() {
        return codigoDeBarras;
    }

    public void setCodigoDeBarras(String codigoDeBarras) {
        this.codigoDeBarras = codigoDeBarras;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }
}
