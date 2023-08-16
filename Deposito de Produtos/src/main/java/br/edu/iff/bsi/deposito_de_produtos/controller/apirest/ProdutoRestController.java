package br.edu.iff.bsi.deposito_de_produtos.controller.apirest;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/produto")
public class ProdutoRestController {
    @Autowired
    private ProdutoService service;
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addProduto(@RequestBody Produto produto){
        String p = service.addProduto(produto);
        return (p != null) ? ResponseEntity.status(HttpStatus.CREATED).body(p)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto " + produto.getDescricao() + " já existe");
    }
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> updateProduto(@PathVariable("id") Long id, @RequestBody Produto produto){
        produto.trim();
        try{
            Produto p = service.updateProduto(id, produto);
            return (p.getDescricao() == produto.getDescricao()) ? ResponseEntity.status(HttpStatus.OK).body("O produto foi atualizado com sucesso!")
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O produto não foi atualizado com sucesso!");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe produto com a descricao ou codigo de barras atual!");
        }
    }
    @GetMapping
    @ResponseBody
    public List<Produto> getAllProdutos(){
        return service.findAllProdutos();
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deletarProduto(@PathVariable("id") Long id){
        service.deletarProduto(id);
        return ResponseEntity.status(HttpStatus.OK).body("Produto do id "+ id +" foi deletado!");
    }
}
