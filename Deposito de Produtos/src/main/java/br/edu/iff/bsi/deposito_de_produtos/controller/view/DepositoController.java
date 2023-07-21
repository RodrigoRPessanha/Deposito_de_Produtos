package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.Deposito;
import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.repository.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DepositoController {
    @Autowired
    private DepositoRepository res;
    @PostMapping("/addDeposito")
    @ResponseBody
    public String addDeposito(@ModelAttribute Deposito deposito){
        Deposito d = res.save(deposito);
        return "Deposito adicionado";
    }
    @GetMapping("/getAllDepositos")
    @ResponseBody
    public List<Deposito> getAllDepositos(){
        return res.findAll();
    }
}
