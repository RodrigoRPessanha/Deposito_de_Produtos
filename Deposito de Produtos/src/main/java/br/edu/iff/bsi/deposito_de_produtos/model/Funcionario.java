package br.edu.iff.bsi.deposito_de_produtos.model;


import jakarta.persistence.*;


import java.util.Collection;

@Entity
public class Funcionario{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(nullable = true)
    private String nome;
    @Column(nullable = true)
    private String cpf;
    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private FuncaoEnum funcao;

    public Funcionario() {
    }

    @Column(nullable = true)
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id", nullable = true)
    private Endereco endereco;
    @ElementCollection
    @Column(nullable = true)
    private Collection<String> telefone;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "setor_id", referencedColumnName = "id", nullable = true)
    private SetorDeposito setor;

    public Funcionario(Long id, String nome, String cpf, FuncaoEnum funcao, String email, Endereco endereco, String telefone, SetorDeposito setor) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.funcao = funcao;
        this.email = email;
        this.endereco = endereco;
        this.telefone.add(telefone);
        this.setor = setor;
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

    public Collection<String> getTelefone() {
        return telefone;
    }

    public void addTelefone(String telefone) {
        this.telefone.add(telefone);
    }
}
