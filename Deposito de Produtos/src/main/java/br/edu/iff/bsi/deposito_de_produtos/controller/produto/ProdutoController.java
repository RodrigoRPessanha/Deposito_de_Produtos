package br.edu.iff.bsi.deposito_de_produtos.controller.produto;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ProdutoController {
    @Autowired
    private ProdutoRepository res;
    @PostMapping("/addProduto")
    @ResponseBody
    public String addTelefone(@ModelAttribute Produto produto){
        Produto p = res.save(produto);
        return "Produto adicionado --> " + p.getDescricao();
    }
}
