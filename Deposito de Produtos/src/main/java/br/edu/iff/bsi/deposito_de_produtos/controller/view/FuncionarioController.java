package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.FuncaoEnum;
import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import br.edu.iff.bsi.deposito_de_produtos.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioService service;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(FuncaoEnum.class, new FuncaoEnumConverter());
    }

    @PostMapping("/addFuncionario")
    @ResponseBody
    public String addFuncionario(@ModelAttribute Funcionario funcionario){
        String f = service.addFuncionario(funcionario);
        return (f != null) ? f : "Produto " + funcionario.getNome() + " já existe";
    }

    @PostMapping("/updateFuncionario")
    @ResponseBody
    public String updateFuncionario(@ModelAttribute Funcionario funcionario){
        try{
            Funcionario e = service.updateFuncionario(funcionario);
            return (e.getCpf() == funcionario.getCpf()) ? "O funcionário foi atualizado com sucesso!" : "O funcionário não foi atualizado com sucesso!";
        } catch(Exception e){
            return "Não existe funcionário com as informações fornecidas!";
        }
    }

    @PostMapping("/deleteFuncionario")
    @ResponseBody
    public String deletarFuncionario(String cpf){
        service.deletarFuncionario(cpf.trim());
        return "Funcionario do cpf " + cpf + " deletado!";
    }

    @GetMapping("/getAllFuncionarios")
    @ResponseBody
    public List<Funcionario> getAllFuncionarios(){
        return service.findAllFuncionarios();
    }
    @GetMapping("/getAllFuncEnd")
    @ResponseBody
    public List<String> findAllFuncEnd(){
        return service.findAllFuncEnd();
    }
    @GetMapping("/getAllFuncSetor")
    @ResponseBody
    public List<String> findAllFuncSetor(){
        return service.findAllFuncSetor();
    }

}
