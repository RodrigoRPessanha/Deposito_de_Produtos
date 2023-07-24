package br.edu.iff.bsi.deposito_de_produtos.service;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository res;
    public String addProduto(@ModelAttribute Produto produto){
        produto.trim();
        Produto p = res.findById(res.findByDescricao(produto.getDescricao(), produto.getCodigoDeBarras())).get();
        return (produto.getDescricao() == p.getDescricao()) ? null : "Produto " + res.save(produto).getDescricao() + " adicionado";
    }
    public Produto updateProduto(String descricaoAtual,String codigodeBarrasAtual, String descricaoNova, String codigoDeBarrasNovo, int quantidadeNova, BigDecimal precoCustoNovo){
        Produto produto;
        Optional<Produto> p = res.findById(res.findByDescricao(descricaoAtual, codigodeBarrasAtual));
        if (p.isPresent()){
            produto = p.get();
            produto.setDescricao(descricaoNova);
            produto.setCodigoDeBarras(codigoDeBarrasNovo);
            produto.setQuantidade(quantidadeNova);
            produto.setPrecoCusto(precoCustoNovo);
            produto = res.save(produto);
        }else{
            produto = null;
        }
        return produto;
    }
    public void deletarProduto(String descricao, String codigoDeBarras){
        res.deleteById(res.findByDescricao(descricao,codigoDeBarras));
    }
    public List<Produto> findAllProdutos(){
        return res.findAll();
    }
}
