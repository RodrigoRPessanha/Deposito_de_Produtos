package br.edu.iff.bsi.deposito_de_produtos.controller.apirest;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/setor")
public class SetorRestController {
    @Autowired
    private SetorService service;
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addSetor(@RequestBody  SetorDeposito setor){
        String s = service.addSetor(setor);
        return (s != null) ? ResponseEntity.status(HttpStatus.CREATED).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deposito " + setor.getDescricao() + " já existe");
    }
    @PutMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<String> updateSetor(@PathVariable("id") Long id, @RequestParam String descricaoNova){
        try{
            SetorDeposito s = service.updateSetor(id, descricaoNova);
            return (s.getDescricao() == descricaoNova) ? ResponseEntity.status(HttpStatus.OK).body("O setor foi atualizado com sucesso!")
                                                       : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O setor não foi atualizado com sucesso!");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe setor com o id atual!");
        }
    }

    @PutMapping(path = "/{id}/produtos")
    @ResponseBody
    public ResponseEntity<String> addProduto(@PathVariable("id") Long setorId, @RequestBody Produto produto){
        Long produtoId = produto.getId();
        String s = service.addProdutos(setorId, produtoId);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto do id " + produtoId + " já existe no setor");
    }

    @DeleteMapping(path = "/{idSetor}/produtos/{idProduto}")
    @ResponseBody
    public ResponseEntity<String> deletarProduto(@PathVariable("idSetor") Long idSetor, @PathVariable("idProduto") Long idProduto){
        String s = service.removerProdutos(idSetor, idProduto);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto do id " + idProduto + " não existe no setor");
    }
    @GetMapping
    @ResponseBody
    public List<SetorDeposito> findAllSetores(){
        return service.findAllSetores();
    }

    @DeleteMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<String> deletarSetor(@PathVariable("id") Long id){
        service.deletarSetor(id);
        return ResponseEntity.status(HttpStatus.OK).body("Setor do id "+ id +" deletado!");
    }
}
