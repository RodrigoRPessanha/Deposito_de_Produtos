package br.edu.iff.bsi.deposito_de_produtos.controller.setor;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class SetorController {
    @Autowired
    private SetorRepository res;
    @PostMapping("/addSetor")
    @ResponseBody
    public String addSetor(@ModelAttribute SetorDeposito setor){
        SetorDeposito s = res.save(setor);
        return "Telefone adicionado --> " + s.getDescricao();
    }
    @GetMapping("/getSetores")
    @ResponseBody
    public List<SetorDeposito> getSetores(){
        return res.selectAllSetor();
    }
}