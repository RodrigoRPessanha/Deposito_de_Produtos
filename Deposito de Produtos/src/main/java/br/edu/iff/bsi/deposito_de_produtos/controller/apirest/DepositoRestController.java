package br.edu.iff.bsi.deposito_de_produtos.controller.apirest;

import br.edu.iff.bsi.deposito_de_produtos.model.Deposito;
import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String addDeposito(@RequestBody Deposito deposito){

        String s = service.addDeposito(deposito);
        return (s != null) ? s : "Deposito " + deposito.getDescricao() + " já existe";
    }

    @PutMapping(path = "/{id}/setor")
    @ResponseBody
    public String addSetor(@PathVariable("id") Long depositoId, @RequestBody SetorDeposito setorDeposito){
        Long setorId = setorDeposito.getId();
        String s = service.addSetores(depositoId, setorId);
        return (s != null) ? s : "Setor do id: " + setorId + " já existe no Deposito";
    }
    @PutMapping(path = "/{id}")
    @ResponseBody
    public String updateDeposito(@PathVariable("id") Long id, @RequestParam String descricaoNova){
        try{
            Deposito s = service.updateDeposito(id, descricaoNova.trim());
            return (s.getDescricao().equals(descricaoNova)) ? "O depósito foi atualizado com sucesso!" : "O depósito não foi atualizado com sucesso!";
        } catch(Exception e){
            System.out.println(e.getMessage());
            return "Não existe depósito com a descricao atual!";
        }
    }
    @DeleteMapping(path = "/{id}")
    @ResponseBody
    public String deleteDeposito(@PathVariable("id") Long id){
        service.deletarDeposito(id);
        return "Depósito do id"+ id +" deletado!";
    }
}
