package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.FuncaoEnum;
import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import br.edu.iff.bsi.deposito_de_produtos.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Map<String, String> addFuncionario(@Valid @ModelAttribute Funcionario funcionario, BindingResult result){
        Map<String, String> response = new HashMap<>();

        if (result.hasErrors()) {
            response.put("error", "Algum dos campos Obrigatórios está vazio");
        } else {
            String f = service.addFuncionario(funcionario);
            response.put("message", (f != null) ? f : "Produto " + funcionario.getNome() + " já existe");
        }
        return response;
    }

    @PostMapping("/updateFuncionario")
    @ResponseBody
    public Map<String, String> updateFuncionario(Long id, @Valid @ModelAttribute Funcionario funcionario, BindingResult result){
        Map<String, String> response = new HashMap<>();
        if (result.hasErrors()) {
            response.put("error", "Descrição e Id Obrigatórios");
        } else {
            try {
                Funcionario f = service.updateFuncionario(id, funcionario);
                response.put("message", (f.getId() == funcionario.getId()) ? "O funcionário foi atualizado com sucesso!" : "O funcionário não foi atualizado com sucesso!");
            } catch (Exception e) {
                response.put("error", "Algum dos campos Obrigatórios está vazio");
            }
        }
        return response;
    }

    @PostMapping("/deleteFuncionario")
    @ResponseBody
    public Map<String, String> deletarFuncionario(Long id){
        Map<String, String> response = new HashMap<>();
        Funcionario f = service.findFuncionarioById(id);
        String mensagem = (f == null) ? "Funcionario não encontrado" : "Funcionario do id " + id + " deletado!";
        service.deletarFuncionario(id);
        response.put("message", mensagem);
        return response;
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
