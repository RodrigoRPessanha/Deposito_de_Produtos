package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.Endereco;
import br.edu.iff.bsi.deposito_de_produtos.model.FuncaoEnum;
import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import br.edu.iff.bsi.deposito_de_produtos.service.EnderecoService;
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
    private FuncionarioService funcionarioService;
    @Autowired
    private EnderecoService enderecoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(FuncaoEnum.class, new FuncaoEnumConverter());
    }

    @PostMapping("/addFuncionario")
    @ResponseBody
    public Map<String, String> addFuncionario(@Valid @ModelAttribute Funcionario funcionario, @Valid @ModelAttribute Endereco endereco, BindingResult result){
        Map<String, String> response = new HashMap<>();

        if (result.hasErrors()) {
            response.put("error", "Algum dos campos Obrigatórios está vazio");
        } else {
            Endereco e = enderecoService.addEndereco(endereco);
            String f = funcionarioService.addFuncionario(funcionario, e.getId());
            response.put("message", (f != null) ? f : "Produto " + funcionario.getNome() + " já existe");
        }
        return response;
    }

    @PostMapping("/updateFuncionario")
    @ResponseBody
    public Map<String, String> updateFuncionario(Long id, @Valid @ModelAttribute Funcionario funcionario, @Valid @ModelAttribute Endereco endereco, BindingResult result){
        Map<String, String> response = new HashMap<>();
        if (result.hasErrors()) {
            response.put("error", "Descrição e Id Obrigatórios");
        } else {
            try {
                Endereco e = enderecoService.updateEndereco(funcionarioService.findFuncionarioById(id).getEndereco().getId(),endereco);
                Funcionario f = funcionarioService.updateFuncionario(id, funcionario, e.getId());
                response.put("message", (f.getId() == funcionario.getId()) ? "O funcionário foi atualizado com sucesso!" : "O funcionário não foi atualizado com sucesso!");
            } catch (Exception e) {
                response.put("error", "Algum dos campos Obrigatórios está vazio");
            }
        }
        return response;
    }

    @PostMapping("/addSetor")
    @ResponseBody
    public Map<String, String> addSetor(@RequestParam("funcionarioId") Long idFuncionario, @RequestParam("setorId") Long idSetor){
        Map<String, String> response = new HashMap<>();
        String s = funcionarioService.setarSetor(idFuncionario, idSetor);
        response.put("message", (s != null) ? s : "Setor com o id " + idSetor + " já está cadastrado no funcionário com o id " + idFuncionario);
        return response;
    }
    @PostMapping("/deleteSetor")
    @ResponseBody
    public Map<String, String> removerSetor(@RequestParam("id") Long id){
        Map<String, String> response = new HashMap<>();
        String s = funcionarioService.removerSetor(id);
        response.put("message", (s != null) ? s : "Funcionário não existe");
        return response;
    }

    @PostMapping("/deleteFuncionario")
    @ResponseBody
    public Map<String, String> deletarFuncionario(Long id){
        Map<String, String> response = new HashMap<>();
        Funcionario f = funcionarioService.findFuncionarioById(id);
        String mensagem = (f == null) ? "Funcionario não encontrado" : "Funcionario do id " + id + " deletado!";
        funcionarioService.deletarFuncionario(id);
        response.put("message", mensagem);
        return response;
    }

    @GetMapping("/getAllFuncionarios")
    @ResponseBody
    public List<Funcionario> getAllFuncionarios(){
        return funcionarioService.findAllFuncionarios();
    }
    @GetMapping("/getEnderecos")
    @ResponseBody
    public List<Endereco> findAllEnderecos(){
        return enderecoService.findAllEnderecos();
    }
    @GetMapping("/getFuncSetor")
    @ResponseBody
    public List<String> findAllFuncSetor(Long id){
        return funcionarioService.findSetorFromFunc(id);
    }

}
