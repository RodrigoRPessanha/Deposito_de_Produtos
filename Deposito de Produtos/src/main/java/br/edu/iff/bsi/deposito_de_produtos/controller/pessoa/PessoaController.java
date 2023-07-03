package br.edu.iff.bsi.deposito_de_produtos.controller.pessoa;

import br.edu.iff.bsi.deposito_de_produtos.model.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
public class PessoaController {

    @PostMapping("/savePessoa")
    @ResponseStatus(HttpStatus.CREATED)
    public void savePessoa(@ModelAttribute Pessoa pessoa){
        System.out.println("nome = " + pessoa.getNome());
        System.out.println("email = " + pessoa.getEmail());
        System.out.println("cpf = " + pessoa.getCpf());
    }
}
