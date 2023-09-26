package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.Deposito;
import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.service.DepositoService;
import br.edu.iff.bsi.deposito_de_produtos.service.FuncionarioService;
import br.edu.iff.bsi.deposito_de_produtos.service.ProdutoService;
import br.edu.iff.bsi.deposito_de_produtos.service.SetorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllerView {

    @Autowired
    private SetorService setorService;
    @Autowired
    private DepositoService depositoService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping(path = "/home")
    public String page(){
        return "layoutBase";
    }

    @GetMapping
    public String Setor(Model model){
        SetorDeposito s = new SetorDeposito();
        Deposito d = new Deposito();
        Funcionario f = new Funcionario();
        Produto p = new Produto();

        model.addAttribute("setor", s);
        model.addAttribute("deposito", d);
        model.addAttribute("produto", p);
        model.addAttribute("funcionario", f);
        return "index";
    }
}
