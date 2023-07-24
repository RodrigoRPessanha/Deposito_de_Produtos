package br.edu.iff.bsi.deposito_de_produtos.repository;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query(value = "SELECT ID FROM PRODUTO where DESCRICAO LIKE %?1% OR CODIGO_DE_BARRAS LIKE %?2% ", nativeQuery = true)
    Long findByDescricao(String buscarDescricao, String codigoDeBarras);
}
