package br.edu.iff.bsi.deposito_de_produtos.controller.apirest;

import br.edu.iff.bsi.deposito_de_produtos.model.Endereco;
import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import br.edu.iff.bsi.deposito_de_produtos.model.FuncionarioComEndereco;
import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.service.EnderecoService;
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
    private FuncionarioService funcionarioService;
    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> addFuncionario(@RequestBody FuncionarioComEndereco funcionarioComEndereco){
        Funcionario funcionario = funcionarioComEndereco.getFuncionario();
        Endereco endereco = funcionarioComEndereco.getEndereco();
        Endereco e = enderecoService.addEndereco(endereco);
        String f = funcionarioService.addFuncionario(funcionario, e.getId());
        return (f != null) ? ResponseEntity.status(HttpStatus.CREATED).body(f)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Produto " + funcionario.getNome() + " já existe");
    }
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> updateFuncionario(@PathVariable("id") Long id, @RequestBody FuncionarioComEndereco funcionarioComEndereco){
        try{
            Funcionario funcionario = funcionarioComEndereco.getFuncionario();
            Endereco endereco = funcionarioComEndereco.getEndereco();
            Endereco e = enderecoService.updateEndereco(funcionarioService.findFuncionarioById(id).getEndereco().getId(),endereco);
            Funcionario f = funcionarioService.updateFuncionario(id, funcionario, e.getId());
            return (f != null) ? ResponseEntity.status(HttpStatus.OK).body("O funcionário foi atualizado com sucesso!")
                               : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("O funcionário não foi atualizado com sucesso!");
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não existe funcionário com as informações fornecidas!");
        }
    }
    @PutMapping("/{id}/setor")
    @ResponseBody
    public ResponseEntity<String> addSetor(@PathVariable("id") Long id, @RequestBody SetorDeposito setor){
        Long idSetor = setor.getId();
        String s = funcionarioService.setarSetor(id, idSetor);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Setor " + setor.getDescricao() + " já está cadastrado no funcionário");
    }
    @DeleteMapping("/{id}/setor")
    @ResponseBody
    public ResponseEntity<String> removerSetor(@PathVariable("id") Long id){
        String s = funcionarioService.removerSetor(id);
        return (s != null) ? ResponseEntity.status(HttpStatus.OK).body(s)
                           : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Funcionário não existe");
    }
    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<String> deletarFuncionario(@PathVariable("id") Long id){
        funcionarioService.deletarFuncionario(id);
        return ResponseEntity.status(HttpStatus.OK).body("Funcionario do id " + id + " foi deletado!");
    }
    @GetMapping
    @ResponseBody
    public List<Funcionario> getAllFuncionarios(){
        return funcionarioService.findAllFuncionarios();
    }

    @GetMapping(path = "/{id}/telefones")
    @ResponseBody
    public List<String> findTelFromFunc(@PathVariable("id") Long id){
        return funcionarioService.findTelFromFunc(id);
    }
    @GetMapping(path = "/{id}/endereco")
    @ResponseBody
    public List<String> findEndFromFunc(@PathVariable("id") Long id){
        return funcionarioService.findEndFromFunc(id);
    }
    @GetMapping(path = "/{id}/setor")
    @ResponseBody
    public List<String> findSetorFromFunc(@PathVariable("id") Long id){
        return funcionarioService.findSetorFromFunc(id);
    }


}
