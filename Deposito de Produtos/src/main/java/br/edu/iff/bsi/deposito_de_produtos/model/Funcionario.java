package br.edu.iff.bsi.deposito_de_produtos.model;


import jakarta.persistence.*;

@Entity
public class Funcionario{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String nome;
    private String cpf;
    @Enumerated(EnumType.ORDINAL)
    private FuncaoEnum funcao;
    private String email;

    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    @OneToOne
    @JoinColumn(name = "telefone_id", referencedColumnName = "id")
    private Telefone telefone;

    @OneToOne
    @JoinColumn(name = "setor_id", referencedColumnName = "id")
    private SetorDeposito setor;

    public Funcionario(Long id, String nome, String cpf, String email, Endereco endereco, Telefone telefone, FuncaoEnum funcao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
        this.telefone = telefone;
        this.funcao = funcao;
    }


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
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
        this.email = email;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }
}
