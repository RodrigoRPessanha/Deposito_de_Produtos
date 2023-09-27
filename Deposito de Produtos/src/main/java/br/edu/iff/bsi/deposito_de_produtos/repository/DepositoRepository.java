package br.edu.iff.bsi.deposito_de_produtos.repository;

import br.edu.iff.bsi.deposito_de_produtos.model.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {
    @Query(value = "SELECT ID FROM DEPOSITO where DESCRICAO LIKE %?1%", nativeQuery = true)
    Long findByDescricao(String buscarDescricao);

    @Query(value = "SELECT s.DESCRICAO " +
            "FROM DEPOSITO_SETORES ds " +
            "join SETOR_DEPOSITO s " +
            "on s.id = ds.SETORES_ID " +
            "join DEPOSITO d " +
            "on d.id = ds.DEPOSITO_ID " +
            "where d.id = ?1", nativeQuery = true)
    List<String> findSetorByDepositoId(Long idDeposito);
}
