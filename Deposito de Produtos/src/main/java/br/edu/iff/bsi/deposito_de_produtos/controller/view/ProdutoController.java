package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProdutoController {
    @Autowired
    private ProdutoRepository res;
    @PostMapping("/addProduto")
    @ResponseBody
    public String addProduto(@ModelAttribute Produto produto){
        Produto p = res.save(produto);
        return "Produto adicionado --> " + p.getDescricao();
    }

    @GetMapping("/getAllProdutos")
    @ResponseBody
    public List<Produto> getAllProdutos(){
        return res.findAll();
    }
}
