package br.edu.iff.bsi.deposito_de_produtos.controller.setor;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SetorController {
    @Autowired
    private SetorRepository res;
    @PostMapping("/addSetor")
    @ResponseBody
    public String addTelefone(@ModelAttribute SetorDeposito setor){
        SetorDeposito s = res.save(setor);
        return "Telefone adicionado --> " + s.getDescricao();
    }
}
