package br.edu.iff.bsi.deposito_de_produtos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Rua obrigatória.")
    @Column(nullable = false)
    private String rua;
    @NotBlank(message = "Numero obrigatório.")
    @Column(nullable = false)
    private String numero;
    @NotBlank(message = "Bairro obrigatório.")
    @Column(nullable = false)
    private String bairro;
    @NotBlank(message = "Cidade obrigatória.")
    @Column(nullable = false)
    private String cidade;
    @NotBlank(message = "Estado obrigatório.")
    @Column(nullable = false)
    private String estado;
    @NotBlank(message = "CEP obrigatório.")
    @Column(nullable = false)
    private String cep;

    public String enderecoFormatado(){
        return this.rua + ", " + this.numero + " - " + this.bairro + ", " + this.cidade + " - " + this.estado + ", " + this.cep;
    }
    public void trim(){
        this.rua = this.rua.trim();
        this.numero = this.numero.trim();
        this.bairro = this.bairro.trim();
        this.cidade = this.cidade.trim();
        this.estado = this.estado.trim();
        this.cep = this.cep.trim();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
