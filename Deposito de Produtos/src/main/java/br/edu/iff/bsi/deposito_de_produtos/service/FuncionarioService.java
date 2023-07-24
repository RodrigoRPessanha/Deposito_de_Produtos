package br.edu.iff.bsi.deposito_de_produtos.service;

import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import br.edu.iff.bsi.deposito_de_produtos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository res;
    public String addFuncionario(@ModelAttribute Funcionario funcionario){
        Funcionario f = funcionario;
        try{
            f = res.findById(funcionario.getId()).get();
            return null;
        }catch(Exception e){
            return "Funcionario " + res.save(funcionario).getNome() + " adicionado";
        }
    }
    public Funcionario updateFuncionario(@ModelAttribute Funcionario funcionario){
        Funcionario funcionarioNovo;
        Optional<Funcionario> p = res.findById(funcionario.getId());
        if (p.isPresent()){
            funcionarioNovo = p.get();
            funcionarioNovo.setNome(funcionario.getNome());
            funcionarioNovo.setCpf(funcionario.getCpf());
            funcionarioNovo.setEmail(funcionario.getEmail());
            funcionarioNovo.setEndereco(funcionario.getEndereco());
            funcionarioNovo.setFuncao(funcionario.getFuncao());
            funcionarioNovo.setTelefone(funcionario.getTelefone());
            funcionarioNovo = res.save(funcionarioNovo);
        }else{
            funcionarioNovo = null;
        }
        return funcionarioNovo;
    }

    public void deletarFuncionario(String cpf){
        res.deleteById(res.findByCPF(cpf));
    }
    public List<Funcionario> findAllFuncionarios(){
        return res.findAll();
    }


    public List<String> findAllFuncEnd(){
        return res.findAllFuncEnd();
    }

    public List<String> findAllFuncSetor(){
        return res.findAllFuncSetor();
    }
}
