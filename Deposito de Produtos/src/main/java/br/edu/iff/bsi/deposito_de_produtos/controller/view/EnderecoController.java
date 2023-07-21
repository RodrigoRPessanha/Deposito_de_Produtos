package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import br.edu.iff.bsi.deposito_de_produtos.model.Endereco;
import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class EnderecoController {
    @Autowired
    private EnderecoRepository res;
    @PostMapping("/addEndereco")
    @ResponseBody
    public String addEndereco(@ModelAttribute Endereco endereco){
        Endereco e = res.save(endereco);
        return "Endereço adicionado --> " + e.enderecoFormatado();
    }
    @GetMapping("/getAllEnderecos")
    @ResponseBody
    public List<Endereco> getAllEnderecos(){
        return res.findAll();
    }
}
