package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/setor")
public class SetorController {
    @Autowired
    private SetorService service;

    @PostMapping("/addSetor")
    @ResponseBody
    public String addSetor(@ModelAttribute SetorDeposito setor){
        String s = service.addSetor(setor);
        return (s != null) ? s : "Deposito " + setor.getDescricao() + " já existe";
    }
    @PostMapping("/updateSetor")
    @ResponseBody
    public String updateSetor(String descricaoAtual, String descricaoNova){
        try{
            SetorDeposito s = service.updateSetor(descricaoAtual, descricaoNova);
            return (s.getDescricao() == descricaoNova) ? "O setor foi atualizado com sucesso!" : "O setor não foi atualizado com sucesso!";
        } catch(Exception e){
            return "Não existe setor com a descricao atual!";
        }
    }

    @GetMapping("/getAllSetores")
    @ResponseBody
    public List<SetorDeposito> findAllSetores(){
        return service.findAllSetores();
    }

    @PostMapping("/deleteSetor")
    @ResponseBody
    public String deletarSetor(String descricao){
        service.deletarSetor(descricao.trim());
        return "Setor "+ descricao.trim() +" deletado!";
    }

}