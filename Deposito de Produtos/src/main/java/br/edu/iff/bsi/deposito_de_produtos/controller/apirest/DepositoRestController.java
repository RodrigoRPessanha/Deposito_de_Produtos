package br.edu.iff.bsi.deposito_de_produtos.controller.apirest;

import br.edu.iff.bsi.deposito_de_produtos.model.Deposito;
import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/deposito")
public class DepositoRestController {

    @Autowired
    private DepositoService service;
    @GetMapping
    @ResponseBody
    public List<Deposito> getDeposito(){
        return service.findAllDepositos();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addDeposito(@RequestBody Deposito deposito){
        String s = service.addDeposito(deposito);
        return (s != null) ? ResponseEntity.status(HttpStatus.CREATED).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Deposito " + deposito.getDescricao() + " já existe");
    }

    @PutMapping(path = "/{id}/setores")
    @ResponseBody
    public ResponseEntity<String> addSetor(@PathVariable("id") Long depositoId, @RequestBody SetorDeposito setorDeposito){
        Long setorId = setorDeposito.getId();
        String s = service.addSetores(depositoId, setorId);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Setor do id: " + setorId + " já existe no Deposito");
    }

    @PutMapping(path = "/{idDeposito}/setores/{idSetor}")
    @ResponseBody
    public ResponseEntity<String> deletarSetor(@PathVariable("idDeposito") Long idDeposito, @PathVariable("idSetor") Long idSetor){
        String s = service.removerSetor(idDeposito, idSetor);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Setor do id: " + idSetor + " já existe no Deposito");
    }
    @PutMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<String> updateDeposito(@PathVariable("id") Long id, @RequestParam String descricaoNova){
        try{
            Deposito s = service.updateDeposito(id, descricaoNova.trim());
            return (s.getDescricao().equals(descricaoNova)) ? ResponseEntity.status(HttpStatus.CREATED).body("O depósito foi atualizado com sucesso!")
                                                            : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O depósito não foi atualizado com sucesso!");
        } catch(Exception e){
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe depósito com a descricao atual!");
        }
    }
    @DeleteMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<String> deleteDeposito(@PathVariable("id") Long id){
        service.deletarDeposito(id);
        return ResponseEntity.status(HttpStatus.OK).body("Depósito do id"+ id +" deletado!");
    }
}
