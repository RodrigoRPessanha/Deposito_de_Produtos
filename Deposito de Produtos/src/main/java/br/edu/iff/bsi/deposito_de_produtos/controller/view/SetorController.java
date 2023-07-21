package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.repository.SetorRepository;
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
        SetorDeposito s = service.salvarSetor(setor);
        return "Setor adicionado --> " + s.getDescricao();
    }@PostMapping("/updateSetor")
    @ResponseBody
    public String updateSetor(String descricaoAtual, String descricaoNova){
        SetorDeposito s = service.updateSetor(descricaoAtual, descricaoNova);
        return "Setor adicionado --> " + s.getDescricao();
    }

    @GetMapping("/getAllSetores")
    @ResponseBody
    public List<SetorDeposito> findAllSetores(){
        return service.findAllSetores();
    }

    @PostMapping("/deleteSetor")
    @ResponseBody
    public String deletarSetor(@ModelAttribute SetorDeposito setor){
        service.deletarSetor(setor);
        return "Setor deletado!";
    }

}