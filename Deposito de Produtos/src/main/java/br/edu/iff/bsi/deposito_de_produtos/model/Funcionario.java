package br.edu.iff.bsi.deposito_de_produtos.model;


import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cpf;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuncaoEnum funcao;
    @Column(nullable = false)
    private String email;



    @ElementCollection
    @Column(nullable = false)
    private Collection<String> telefone = new ArrayList<String>();
    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = true)
    private Endereco endereco;
    @OneToOne
    @JoinColumn(name = "setor_id", referencedColumnName = "id", nullable = true)
    private SetorDeposito setor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf.trim();
    }

    public FuncaoEnum getFuncao() {
        return funcao;
    }

    public void setFuncao(FuncaoEnum funcao) {
        this.funcao = funcao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email.trim();
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Collection<String> getTelefone() {
        return telefone;
    }

    public void addTelefone(String telefone) {
        this.telefone.add(telefone.trim());
    }
    public void setTelefone(Collection<String> telefones) {
        this.telefone = telefone;
    }

    public void removeTelefone(String telefone) {
        this.telefone.remove(telefone);
    }

    public SetorDeposito getSetor() {
        return setor;
    }

    public void setSetor(SetorDeposito setor) {
        this.setor = setor;
    }
}
