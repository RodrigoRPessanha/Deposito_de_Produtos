package br.edu.iff.bsi.deposito_de_produtos.controller.funcionario;

import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import br.edu.iff.bsi.deposito_de_produtos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository res;
    @PostMapping("/addFuncionario")
    @ResponseBody
    public String addTelefone(@ModelAttribute Funcionario funcionario){
        Funcionario f = res.save(funcionario);
        return "Funcionario adicionado --> " + f.getNome();
    }
}
