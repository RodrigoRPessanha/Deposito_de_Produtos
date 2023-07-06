package br.edu.iff.bsi.deposito_de_produtos.repository;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SetorRepository extends JpaRepository<SetorDeposito, Long> {
}
