package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.service.SetorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/setor")
public class SetorController {
    @Autowired
    private SetorService service;

    @PostMapping("/addSetor")
    @ResponseBody
    public Map<String, String> addSetor(@Valid @ModelAttribute SetorDeposito setor, BindingResult result) {
        Map<String, String> response = new HashMap<>();

        if (result.hasErrors()) {
            response.put("error", "Descrição Obrigatória");
        } else {
            String s = service.addSetor(setor);
            response.put("message", (s != null) ? s : "Deposito " + setor.getDescricao() + " já existe");
        }

        return response;
    }

    @PostMapping("/updateSetor")
    @ResponseBody
    public Map<String, String> updateSetor(@Valid @ModelAttribute SetorDeposito setor){
        Map<String, String> response = new HashMap<>();
        try{
            SetorDeposito s = service.updateSetor(setor.getId(), setor.getDescricao());
            response.put("message", (s.getDescricao() == setor.getDescricao()) ? "O setor foi atualizado com sucesso!" : "O setor não foi atualizado com sucesso!");
            return response;
        } catch(Exception e){
            response.put("error", "Descrição Obrigatória");
            return response;
        }
    }

    @GetMapping("/getAllSetores")
    @ResponseBody
    public List<SetorDeposito> findAllSetores(Model model){
        return service.findAllSetores();
    }

    @PostMapping("/deleteSetor")
    @ResponseBody
    public Map<String, String> deletarSetor(Long id){
        Map<String, String> response = new HashMap<>();
        SetorDeposito s = service.findSetorById(id);
        String mensagem = (s == null) ? "Setor não encontrado" : s.getDescricao() +" deletado!";
        service.deletarSetor(id);
        response.put("message", mensagem);
        return response;
    }

    @PostMapping(path = "/addProduto")
    @ResponseBody
    public  Map<String, String> addProduto(@ModelAttribute SetorDeposito setor, @ModelAttribute Produto produto){
        Map<String, String> response = new HashMap<>();
        String s = service.addProdutos(setor.getId(), produto.getId());
        response.put("message", (s != null) ? s : "Produto do id " + produto.getId() + " já existe no setor do id " + setor.getId() + " ou em outros setores");
        return response;
    }

    @PostMapping(path = "/deleteProduto")
    @ResponseBody
    public Map<String, String> deletarProduto(@ModelAttribute SetorDeposito setor, @ModelAttribute Produto produto){
        Map<String, String> response = new HashMap<>();
        String s = service.removerProdutos(setor.getId(), produto.getId());
        response.put("message", (s != null) ? s : "Produto do id " + setor.getId() + " não existe no setor");
        return response;
    }
}