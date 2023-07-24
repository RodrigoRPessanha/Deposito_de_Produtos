package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.Endereco;
import br.edu.iff.bsi.deposito_de_produtos.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/endereco")
public class EnderecoController {
    @Autowired
    private EnderecoService service;
    @PostMapping("/addEndereco")
    @ResponseBody
    public String addEndereco(@ModelAttribute Endereco endereco){
        Endereco e = service.addEndereco(endereco);
        return "Endereço adicionado --> " + e.enderecoFormatado();
    }
    @PostMapping("/updateEndereco")
    @ResponseBody
    public String updateEndereco(@ModelAttribute Endereco endereco){
        try{
            endereco.trim();
            Endereco e = service.updateEndereco(endereco);
            return (e.enderecoFormatado() == endereco.enderecoFormatado()) ? "O endereço foi atualizado com sucesso!" : "O endereço não foi atualizado com sucesso!";
        } catch(Exception e){
            return "Não existe endereço com as informações fornecidas!";
        }
    }
    @GetMapping("/getAllEnderecos")
    @ResponseBody
    public List<Endereco> getAllEnderecos(){
        return service.findAllEnderecos();
    }
    @PostMapping("/deleteEndereco")
    @ResponseBody
    public String deletarEndereco(@ModelAttribute Endereco endereco){
        endereco.trim();
        service.deletarEndereco(endereco);
        return "Endereço " + endereco.enderecoFormatado() + " deletado!";
    }
}
