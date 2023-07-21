package br.edu.iff.bsi.deposito_de_produtos.repository;

import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query(value = "SELECT F.ID, F.NOME, E.RUA, E.NUMERO, E.BAIRRO, E.CIDADE, E.ESTADO, E.CEP FROM FUNCIONARIO F JOIN " +
            "ENDERECO E ON F.ENDERECO_ID = E.ID", nativeQuery = true)
    List<String> selectFuncEnd();
    @Query(value = "SELECT F.NOME, S.DESCRICAO AS SETOR FROM FUNCIONARIO F JOIN SETOR_DEPOSITO S ON F.SETOR_ID = S.ID "
            , nativeQuery = true)
    List<String> selectFuncSetor();
}
