package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.FuncaoEnum;
import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getAllFuncionarios")
    @ResponseBody
    public List<Funcionario> getAllFuncionarios(){
        return res.findAll();
    }
    @GetMapping("/selectFuncEnd")
    @ResponseBody
    public List<String> selectFuncEnd(){
        return res.selectFuncEnd();
    }
    @GetMapping("/selectFuncSetor")
    @ResponseBody
    public List<String> selectFuncSetor(){
        return res.selectFuncSetor();
    }

}
