package br.edu.iff.bsi.deposito_de_produtos.repository;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SetorRepository extends JpaRepository<SetorDeposito, Long> {
    @Query(value = "SELECT ID FROM SETOR_DEPOSITO where DESCRICAO LIKE %?1%", nativeQuery = true)
    Long findByDescricao(String buscarDescricao);

    @Query(value = "SELECT p.DESCRICAO " +
            "FROM SETOR_DEPOSITO_PRODUTOS sp " +
            "join SETOR_DEPOSITO s " +
            "on s.id = sp.SETOR_DEPOSITO_ID " +
            "join PRODUTO p " +
            "on p.id = sp.PRODUTOS_ID " +
            "where s.id = ?1"
            , nativeQuery = true)
    List<String> findProdutoBySetorId(Long idDeposito);
}
