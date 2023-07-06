package br.edu.iff.bsi.deposito_de_produtos.repository;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
