package br.edu.iff.bsi.deposito_de_produtos.controller.telefone;

import br.edu.iff.bsi.deposito_de_produtos.model.Telefone;
import br.edu.iff.bsi.deposito_de_produtos.repository.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TelefoneController {

    @Autowired
    private TelefoneRepository res;
    @PostMapping("/addTelefone")
    @ResponseBody
    public String addTelefone(@ModelAttribute Telefone telefone){
        Telefone t = res.save(telefone);
        return "Telefone adicionado --> " + t.getNumero();
    }
}
