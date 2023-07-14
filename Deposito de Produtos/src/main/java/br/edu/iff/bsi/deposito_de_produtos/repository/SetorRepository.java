package br.edu.iff.bsi.deposito_de_produtos.repository;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SetorRepository extends JpaRepository<SetorDeposito, Long> {
    @Query(value = "SELECT * FROM SETOR_DEPOSITO", nativeQuery = true)
    List<SetorDeposito> selectAllSetor();
}
