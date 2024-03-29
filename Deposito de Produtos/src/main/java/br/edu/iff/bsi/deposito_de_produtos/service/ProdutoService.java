package br.edu.iff.bsi.deposito_de_produtos.service;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository res;
    public String addProduto(@ModelAttribute Produto produto){
        Produto p = produto;
        try{
            produto.trim();
            p = res.findById(res.findByDescricao(produto.getDescricao(), produto.getCodigoDeBarras())).get();
            return null;
        }catch (Exception e){
            return "Produto " + res.save(produto).getDescricao() + " adicionado";
        }
    }
    public Produto updateProduto(Long id, Produto produtoNovo){
        Produto produto;
        Optional<Produto> p = res.findById(id);
        if (p.isPresent()){
            produto = p.get();
            produto.setDescricao(produtoNovo.getDescricao());
            produto.setCodigoDeBarras(produtoNovo.getCodigoDeBarras());
            produto.setQuantidade(produtoNovo.getQuantidade());
            produto.setPrecoCusto(produtoNovo.getPrecoCusto());
            produto = res.save(produto);
        }else{
            produto = null;
        }
        return produto;
    }
    public void deletarProduto(Long id){
        res.deleteById(id);
    }
    public List<Produto> findAllProdutos(){
        return res.findAll();
    }

    public Produto findProdutoById(Long id){
        Optional<Produto> p = res.findById(id);
        return p.orElse(null);
    }
}
