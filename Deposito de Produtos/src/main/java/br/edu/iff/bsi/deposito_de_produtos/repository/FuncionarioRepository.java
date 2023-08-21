package br.edu.iff.bsi.deposito_de_produtos.repository;

import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    @Query(value = "SELECT F.ID, F.NOME, E.RUA, E.NUMERO, E.BAIRRO, E.CIDADE, E.ESTADO, E.CEP FROM FUNCIONARIO F JOIN " +
            "ENDERECO E ON F.ENDERECO_ID = E.ID", nativeQuery = true)
    List<String> findAllFuncEnd();
    @Query(value = "SELECT F.NOME, S.DESCRICAO AS SETOR FROM FUNCIONARIO F JOIN SETOR_DEPOSITO S ON F.SETOR_ID = S.ID "
            , nativeQuery = true)
    List<String> findAllFuncSetor();

    @Query(value = "SELECT ft.TELEFONE  FROM FUNCIONARIO_TELEFONE ft " +
            "join FUNCIONARIO f " +
            "on ft.FUNCIONARIO_ID = f.ID " +
            "where f.id = ?1", nativeQuery = true)
    List<String> findTelFromFunc(Long idDeposito);

    @Query(value = "SELECT " +
            "     e.RUA " +
            "   , e.NUMERO " +
            "   , e.BAIRRO " +
            "   , e.CIDADE " +
            "   , e.ESTADO " +
            "   , e.CEP " +
            "FROM ENDERECO e " +
            "  JOIN FUNCIONARIO f " +
            "     ON f.ENDERECO_ID  = e.ID " +
            "WHERE f.ID = ?1", nativeQuery = true)
    List<String> findEndFromFunc(Long id);

    @Query(value = "SELECT s.DESCRICAO  FROM SETOR_DEPOSITO s " +
                    "JOIN FUNCIONARIO f " +
                    "ON s.ID = f.SETOR_ID " +
                    "where f.ID = ?1", nativeQuery = true)
    List<String> findSetorFromFunc(Long id);
    @Query(value = "SELECT ID FROM FUNCIONARIO where CPF LIKE %?1%", nativeQuery = true)
    Long findByCPF(String cpf);

}
