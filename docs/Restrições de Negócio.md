1. Atributos em geral:
* Atributo geral:
* id
* Pessoa
* nome
* email
* cpf
* Funcionario
* funcao
* SetorDeposito
* descricao
* Telefone
* numero
* Produto
* descricao
* precoCusto
* quantidade
* codigoDeBarras
* Deposito
* Endereco
* rua
* numero
* bairro
* cidade
* cep
2. Aceita valor nulo ou não?
* Nenhum atributo aceita valor nulo
3. Será permitido alteração futura?
* Sim
4. Deverá ser único?
* id
5. Padrão (regex)-> um formato específico com CPF, CEP, CNPJ.
1. CEP = \d{5}-\d{3}
2. CPF = /^[0-9]{3}.?[0-9]{3}.?[0-9]{3}-?[0-9]{2}/
6. Atributos do tipo texto:
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
7. Aceita valores espaço em branco ou não?
* Apenas nome, descricao, rua e bairro aceitam espaço entre as palavras
8. Atributos numéricos:
* id
* precoCusto
* quantidade
9. Aceita valores negativos, positivos ou indiferente?
* Somente valores positivos
10. Atributos de relacionamento entre classes
* 
