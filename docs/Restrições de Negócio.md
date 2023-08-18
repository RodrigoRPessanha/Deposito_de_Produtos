1. Atributos:
    * Atributo geral:
        * id
    * Funcionario
        * nome
        * email
        * cpf
        * funcao
        * telefone    
    * SetorDeposito
        * descricao
    * Produto
        * descricao
        * precoCusto
        * quantidade
        * codigoDeBarras
    * Deposito
       * descricao
    * Endereco
        * rua
        * numero
        * bairro
        * cidade
        * cep
2. Aceita valor nulo ou não?  
    * Deposito aceita SetorDeposito como nulo;
    * SetorDeposito aceita Produto como nulo;
    * Funcionario aceita SetorDeposito, telefone e endereço como nulo;
3. Será permitido alteração futura?
    * Sim
4. Deverá ser único?
    * id
5. Atributos do tipo texto:
    * nome
    * email
    * cpf
    * descricao
    * codigoDeBarras
    * rua
    * numero
    * bairro
    * cidade
    * cep
6. Aceita valores espaço em branco ou não?
    * Apenas nome, descricao, rua e bairro aceitam espaço entre as palavras
7. Atributos numéricos:
    * id
    * precoCusto
    * quantidade
8. Aceita valores negativos, positivos ou indiferente?
    * Somente valores positivos
     
