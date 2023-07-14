package br.edu.iff.bsi.deposito_de_produtos.controller.funcionario;

import br.edu.iff.bsi.deposito_de_produtos.model.FuncaoEnum;
import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import br.edu.iff.bsi.deposito_de_produtos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository res;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(FuncaoEnum.class, new FuncaoEnumConverter());
    }

    @PostMapping("/addFuncionario")
    @ResponseBody
    public String addFuncionario(@ModelAttribute Funcionario funcionario){
        Funcionario f = res.save(funcionario);
        return "Funcionario adicionado --> " + f.getNome();
    }
}
