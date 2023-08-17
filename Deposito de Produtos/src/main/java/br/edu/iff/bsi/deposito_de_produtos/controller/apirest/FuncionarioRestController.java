package br.edu.iff.bsi.deposito_de_produtos.controller.apirest;

import br.edu.iff.bsi.deposito_de_produtos.model.Endereco;
import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/funcionario")
public class FuncionarioRestController {
    @Autowired
    private FuncionarioService service;

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addFuncionario(@RequestBody Funcionario funcionario){
        String f = service.addFuncionario(funcionario);
        return (f != null) ? ResponseEntity.status(HttpStatus.CREATED).body(f)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto " + funcionario.getNome() + " já existe");
    }
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> updateFuncionario(@PathVariable("id") Long id, @RequestBody Funcionario funcionario){
        try{
            Funcionario f = service.updateFuncionario(id, funcionario);
            return (f != null) ? ResponseEntity.status(HttpStatus.OK).body("O funcionário foi atualizado com sucesso!")
                               : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O funcionário não foi atualizado com sucesso!");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe funcionário com as informações fornecidas!");
        }
    }
    @PutMapping("/{id}/telefones")
    @ResponseBody
    public ResponseEntity<String> addTelefone(@PathVariable("id") Long id, @RequestBody String telefone){
        String s = service.addTelefone(id, telefone);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Telefone: " + telefone + " já está cadastrado no funcionário");
    }
    @DeleteMapping("/{id}/telefones")
    @ResponseBody
    public ResponseEntity<String> removerTelefone(@PathVariable("id") Long id, @RequestBody String telefone){
        String s = service.removerTelefone(id, telefone);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Telefone: " + telefone + " não existe no funcionário");
    }@PutMapping("/{id}/endereco")
    @ResponseBody
    public ResponseEntity<String> addEndereco(@PathVariable("id") Long id, @RequestBody Endereco endereco){
        Long enderecoId = endereco.getId();
        String s = service.setarEndereco(id, enderecoId);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Endereco: " + endereco.getId() + " já está cadastrado no funcionário");
    }
    @DeleteMapping("/{id}/endereco")
    @ResponseBody
    public ResponseEntity<String> removerEndereco(@PathVariable("id") Long id){
        String s = service.removerEndereco(id);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Funcionário não existe");
    }
    @PutMapping("/{id}/setor")
    @ResponseBody
    public ResponseEntity<String> addSetor(@PathVariable("id") Long id, @RequestBody SetorDeposito setor){
        Long idSetor = setor.getId();
        String s = service.setarSetor(id, idSetor);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Setor " + setor.getDescricao() + " já está cadastrado no funcionário");
    }
    @DeleteMapping("/{id}/setor")
    @ResponseBody
    public ResponseEntity<String> removerSetor(@PathVariable("id") Long id){
        String s = service.removerSetor(id);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Funcionário não existe");
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deletarFuncionario(@PathVariable("id") Long id){
        service.deletarFuncionario(id);
        return ResponseEntity.status(HttpStatus.OK).body("Funcionario do id " + id + " foi deletado!");
    }
    @GetMapping
    @ResponseBody
    public List<Funcionario> getAllFuncionarios(){
        return service.findAllFuncionarios();
    }


}
