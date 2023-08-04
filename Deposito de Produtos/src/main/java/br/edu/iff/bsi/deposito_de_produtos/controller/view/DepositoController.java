package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.Deposito;
import br.edu.iff.bsi.deposito_de_produtos.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/deposito")
public class DepositoController {
    @Autowired
    private DepositoService service;

    @PostMapping("/addDeposito")
    @ResponseBody
    public String addDeposito(@ModelAttribute Deposito deposito){
        String s = service.addDeposito(deposito);
        return (s != null) ? s : "Deposito " + deposito.getDescricao() + " já existe";
    }
    @PostMapping("/updateDeposito")
    @ResponseBody
    public String updateDeposito(String descricaoAtual, String descricaoNova){
        try{
            Deposito s = service.updateDeposito(descricaoAtual.trim(), descricaoNova.trim());
            return (s.getDescricao() == descricaoNova) ? "O depósito foi atualizado com sucesso!" : "O depósito não foi atualizado com sucesso!";
        } catch(Exception e){
            return "Não existe depósito com a descricao atual!";
        }
    }
    @GetMapping("/getAllDepositoes")
    @ResponseBody
    public List<Deposito> findAllDepositoes(){
        return service.findAllDepositos();
    }

    @PostMapping("/deleteDeposito")
    @ResponseBody
    public String deletarDeposito(String descricao){
        service.deletarDeposito(descricao.trim());
        return "Depósito "+ descricao.trim() +" deletado!";
    }
}
