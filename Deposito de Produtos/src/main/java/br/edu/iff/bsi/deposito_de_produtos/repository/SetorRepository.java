package br.edu.iff.bsi.deposito_de_produtos.repository;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SetorRepository extends JpaRepository<SetorDeposito, Long> {
    @Query(value = "SELECT ID FROM SETOR_DEPOSITO where DESCRICAO LIKE %?1%", nativeQuery = true)
    Long findByDescricao(String buscarDescricao);
}
