package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.service.SetorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    public Map<String, String> updateSetor(@Valid @ModelAttribute SetorDeposito setor, Long id, BindingResult result){
        Map<String, String> response = new HashMap<>();
        if (result.hasErrors()) {
            response.put("error", "Descrição e Id Obrigatórios");
        } else {
            try{
                SetorDeposito s = service.updateSetor(id, setor.getDescricao());
                response.put("message", (s.getDescricao() == setor.getDescricao()) ? "O setor foi atualizado com sucesso!" : "O setor não foi atualizado com sucesso!");
            } catch(Exception e){
                response.put("error", "Descrição e Id Obrigatórios");
            }
        }
        return response;
    }

    @GetMapping("/getAllSetores")
    @ResponseBody
    public List<SetorDeposito> findAllSetores(){
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
    public  Map<String, String> addProduto(@RequestParam("idSetor") Long setorId, @RequestParam("idProduto") Long produtoId){
        Map<String, String> response = new HashMap<>();
        String s = service.addProdutos(setorId, produtoId);
        response.put("message", (s != null) ? s : "Produto do id " + produtoId + " já existe no setor do id " + setorId + " ou em outros setores");
        return response;
    }
    @PostMapping(path = "/deleteProduto")
    @ResponseBody
    public Map<String, String> deletarProduto(@RequestParam("idSetorR") Long setorId, @RequestParam("idProdutoR") Long produtoId){
        Map<String, String> response = new HashMap<>();
        String s = service.removerProdutos(setorId, produtoId);
        response.put("message", (s != null) ? s : "Produto do id " + produtoId + " não existe no setor do id " + setorId);
        return response;
    }

}