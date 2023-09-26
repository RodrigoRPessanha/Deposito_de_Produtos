package br.edu.iff.bsi.deposito_de_produtos.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

@Entity
public class Funcionario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Nome obrigatório.")
    @Column(nullable = false)
    private String nome;
    @NotBlank(message = "CPF obrigatório.")
    @Column(nullable = false)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuncaoEnum funcao;
    @NotBlank(message = "Email obrigatório.")
    @Column(nullable = false)
    private String email;
    @NotBlank(message = "Telefone obrigatório.")
    @Column(nullable = false)
    private String telefone;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public SetorDeposito getSetor() {
        return setor;
    }

    public void setSetor(SetorDeposito setor) {
        this.setor = setor;
    }
}
