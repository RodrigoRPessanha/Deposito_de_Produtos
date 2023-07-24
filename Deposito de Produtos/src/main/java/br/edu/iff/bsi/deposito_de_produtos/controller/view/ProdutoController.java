package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping(path = "/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService service;
    @PostMapping("/addProduto")
    @ResponseBody
    public String addProduto(@ModelAttribute Produto produto){
        String p = service.addProduto(produto);
        return (p != null) ? p : "Produto " + produto.getDescricao() + " já existe";
    }
    @PostMapping("/updateProduto")
    @ResponseBody
    public String updateProduto(String descricaoAtual, String codigodeBarrasAtual, String descricaoNova, String codigoDeBarrasNovo, int quantidadeNova, BigDecimal precoCustoNovo){
        try{
            Produto p = service.updateProduto(descricaoAtual.trim(),codigodeBarrasAtual.trim(), descricaoNova.trim(), codigoDeBarrasNovo.trim(), quantidadeNova, precoCustoNovo);
            return (p.getDescricao() == descricaoNova.trim()) ? "O produto foi atualizado com sucesso!" : "O produto não foi atualizado com sucesso!";
        } catch(Exception e){
            return "Não existe produto com a descricao ou codigo de barras atual!";
        }
    }
    @GetMapping("/getAllProdutos")
    @ResponseBody
    public List<Produto> getAllProdutos(){
        return service.findAllProdutos();
    }
    @PostMapping("/deleteProduto")
    @ResponseBody
    public String deletarProduto(String descricao, String codigoDeBarras){
        service.deletarProduto(descricao.trim(), codigoDeBarras.trim());
        return "Produto "+ descricao.trim() +" deletado!";
    }
}
