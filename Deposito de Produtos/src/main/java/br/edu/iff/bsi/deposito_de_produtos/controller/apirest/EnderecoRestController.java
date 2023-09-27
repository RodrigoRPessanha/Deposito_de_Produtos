package br.edu.iff.bsi.deposito_de_produtos.controller.apirest;

import br.edu.iff.bsi.deposito_de_produtos.model.Endereco;
import br.edu.iff.bsi.deposito_de_produtos.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/endereco")
public class EnderecoRestController {
    @Autowired
    private EnderecoService service;
    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addEndereco(@RequestBody Endereco endereco){
        Endereco e = service.addEndereco(endereco);
        return ResponseEntity.status(HttpStatus.CREATED).body("O Endereço " + e.enderecoFormatado()+" foi adicionado");
    }
    @PutMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<String> updateEndereco(@PathVariable("id") Long id, @RequestBody Endereco endereco){
        try{
            endereco.trim();
            Endereco e = service.updateEndereco(id, endereco);
            return (e.enderecoFormatado() == endereco.enderecoFormatado()) ? ResponseEntity.status(HttpStatus.OK).body("O endereço foi atualizado com sucesso!")
                    : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O endereço não foi atualizado com sucesso!");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe endereço com as informações fornecidas!");
        }
    }
    @GetMapping
    @ResponseBody
    public List<Endereco> getAllEnderecos(){
        return service.findAllEnderecos();
    }
    @DeleteMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<String> deletarEndereco(@PathVariable("id") Long id){
        service.deletarEndereco(id);
        return ResponseEntity.status(HttpStatus.OK).body("Endereço do id " + id + " foi deletado!");
    }
}
