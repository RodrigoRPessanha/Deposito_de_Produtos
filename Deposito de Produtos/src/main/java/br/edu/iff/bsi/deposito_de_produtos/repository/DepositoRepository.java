package br.edu.iff.bsi.deposito_de_produtos.repository;

import br.edu.iff.bsi.deposito_de_produtos.model.Deposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepositoRepository extends JpaRepository<Deposito, Long> {
    @Query(value = "SELECT ID FROM DEPOSITO where DESCRICAO LIKE %?1%", nativeQuery = true)
    Long findByDescricao(String buscarDescricao);
}
