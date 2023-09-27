package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllerView {

    @GetMapping(path = "/layoutbase")
    public String page(){
        return "layoutBase";
    }

    @GetMapping(path = "/home")
    public String Setor(Model model){
        SetorDeposito s = new SetorDeposito();
        Deposito d = new Deposito();
        Funcionario f = new Funcionario();
        Produto p = new Produto();
        Endereco e = new Endereco();

        model.addAttribute("setor", s);
        model.addAttribute("deposito", d);
        model.addAttribute("produto", p);
        model.addAttribute("funcionario", f);
        model.addAttribute("endereco", e);
        return "index";
    }
}
