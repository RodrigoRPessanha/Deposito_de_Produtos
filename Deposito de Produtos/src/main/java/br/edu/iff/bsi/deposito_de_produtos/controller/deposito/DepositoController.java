package br.edu.iff.bsi.deposito_de_produtos.controller.deposito;

import br.edu.iff.bsi.deposito_de_produtos.model.Deposito;
import br.edu.iff.bsi.deposito_de_produtos.repository.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DepositoController {
    @Autowired
    private DepositoRepository res;
    @PostMapping("/addDeposito")
    @ResponseBody
    public String addTelefone(@ModelAttribute Deposito deposito){
        Deposito d = res.save(deposito);
        return "Deposito adicionado";
    }

}
