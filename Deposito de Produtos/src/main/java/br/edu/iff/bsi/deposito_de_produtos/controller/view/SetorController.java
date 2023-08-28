package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/setor")
public class SetorController {
    @Autowired
    private SetorService service;

    @GetMapping
    public String Setor(Model model){
        SetorDeposito s = new SetorDeposito();
        List<SetorDeposito> setores = service.findAllSetores();
        model.addAttribute("setor", s);
        model.addAttribute("descricao", setores);
        return "Setor";
    }

    @PostMapping("/addSetor")
    @ResponseBody
    public String addSetor(@ModelAttribute SetorDeposito setor){
        String s = service.addSetor(setor);
        return (s != null) ? s : "Deposito " + setor.getDescricao() + " já existe";
    }
    @PostMapping("/updateSetor")
    @ResponseBody
    public String updateSetor(Long id, String descricao){
        try{
            SetorDeposito s = service.updateSetor(id, descricao);
            return (s.getDescricao() == descricao) ? "O setor foi atualizado com sucesso!" : "O setor não foi atualizado com sucesso!";
        } catch(Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/getAllSetores")
    @ResponseBody
    public List<SetorDeposito> findAllSetores(Model model){
        return service.findAllSetores();
    }

    @PostMapping("/deleteSetor")
    @ResponseBody
    public String deletarSetor(Long id){
        service.deletarSetor(id);
        return "Setor "+ id +" deletado!";
    }

}