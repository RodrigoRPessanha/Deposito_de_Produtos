package br.edu.iff.bsi.deposito_de_produtos.model;

public class FuncionarioComEndereco {
    private Funcionario funcionario;
    private Endereco endereco;

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
