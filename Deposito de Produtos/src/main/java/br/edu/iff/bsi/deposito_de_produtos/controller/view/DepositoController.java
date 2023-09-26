package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.Deposito;
import br.edu.iff.bsi.deposito_de_produtos.service.DepositoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/deposito")
public class DepositoController {
    @Autowired
    private DepositoService service;

    @PostMapping("/addDeposito")
    @ResponseBody
    public Map<String, String> addDeposito(@ModelAttribute Deposito deposito, BindingResult result){
        Map<String, String> response = new HashMap<>();
        if (result.hasErrors()) {
            response.put("error", "Descrição Obrigatória");
        }
        else {
            String s = service.addDeposito(deposito);
            response.put("message", (s != null) ? s : "Deposito " + deposito.getDescricao() + " já existe");
        }
        return response;
    }
    @PostMapping("/updateDeposito")
    @ResponseBody
    public Map<String, String> updateDeposito(Long id, @ModelAttribute Deposito deposito){
        Map<String, String> response = new HashMap<>();
        try{
            Deposito s = service.updateDeposito(id, deposito.getDescricao());
            response.put("message", (s.getDescricao() == deposito.getDescricao()) ? "O depósito foi atualizado com sucesso!" : "O depósito não foi atualizado com sucesso!");
        } catch(Exception e){
            response.put("error", "Não existe depósito com a descricao atual!");
        }
        return response;
    }
    @GetMapping("/getAllDepositos")
    @ResponseBody
    public List<Deposito> findAllDepositos(){
        return service.findAllDepositos();
    }

    @PostMapping("/deleteDeposito")
    @ResponseBody
    public Map<String, String> deletarDeposito(Long id){
        Map<String, String> response = new HashMap<>();
        Deposito d = service.findDepositoById(id);
        String mensagem = (d == null) ? "Deposito não encontrado" : d.getDescricao() +" deletado!";
        service.deletarDeposito(id);
        response.put("message", mensagem);
        return response;
    }
}
