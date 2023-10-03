package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService service;
    @PostMapping("/addProduto")
    @ResponseBody
    public Map<String, String> addProduto(@Valid @ModelAttribute Produto produto, BindingResult result) {
        Map<String, String> response = new HashMap<>();
        if (result.hasErrors()) {
            response.put("error", "Algum dos campos obrigatórios está vazio");
        } else {
            String p = service.addProduto(produto);
            response.put("message", (p != null) ? p : "Produto " + produto.getDescricao() + " já existe");
        }
        return response;
    }
    @PostMapping("/updateProduto")
    @ResponseBody
    public Map<String, String> updateProduto(Long id, @Valid @ModelAttribute Produto produto, BindingResult result){
        Map<String, String> response = new HashMap<>();
        if (result.hasErrors()) {
            response.put("error", "Algum dos campos obrigatórios está vazio");
        } else {
            try {
                Produto p = service.updateProduto(id, produto);
                response.put("message", (p.getDescricao().equals(produto.getDescricao().trim())) ? "O produto foi atualizado com sucesso!" : "O produto não foi atualizado com sucesso!");
            } catch (Exception e) {
                response.put("error", "Algum dos campos obrigatórios está vazio");
            }
        }
        return response;
    }
    @GetMapping("/getAllProdutos")
    @ResponseBody
    public List<Produto> getAllProdutos(){
        return service.findAllProdutos();
    }
    @PostMapping("/deleteProduto")
    @ResponseBody
    public Map<String, String> deletarProduto(Long id){
        Map<String, String> response = new HashMap<>();
        Produto p = service.findProdutoById(id);
        String mensagem = (p == null) ? "Produto não encontrado": "Produto "+ id +" deletado!";
        service.deletarProduto(id);
        response.put("message", mensagem);
        return response;
    }
}
