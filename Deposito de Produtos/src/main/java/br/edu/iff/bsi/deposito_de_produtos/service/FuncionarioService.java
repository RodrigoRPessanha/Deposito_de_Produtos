package br.edu.iff.bsi.deposito_de_produtos.service;

import br.edu.iff.bsi.deposito_de_produtos.model.Endereco;
import br.edu.iff.bsi.deposito_de_produtos.model.Funcionario;
import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.repository.EnderecoRepository;
import br.edu.iff.bsi.deposito_de_produtos.repository.FuncionarioRepository;
import br.edu.iff.bsi.deposito_de_produtos.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository resFunc;
    @Autowired
    private EnderecoRepository resEndereco;
    @Autowired
    private SetorRepository resSetor;
    public String addFuncionario(@ModelAttribute Funcionario funcionario, Long enderecoId){
        try{
            resFunc.findById(funcionario.getId()).get();
            return null;
        }catch(Exception e){
            Funcionario f = resFunc.save(funcionario);
            setarEndereco(f.getId(), enderecoId);
            return "Funcionario " + f.getNome() + " adicionado";
        }
    }
    public Funcionario updateFuncionario(Long id, @ModelAttribute Funcionario funcionario, Long enderecoId){
        Funcionario funcionarioNovo;
        Optional<Funcionario> p = resFunc.findById(id);
        if (p.isPresent()){
            funcionarioNovo = p.get();
            funcionarioNovo.setNome(funcionario.getNome());
            funcionarioNovo.setCpf(funcionario.getCpf());
            funcionarioNovo.setEmail(funcionario.getEmail());
            funcionarioNovo.setEndereco(funcionario.getEndereco());
            funcionarioNovo.setFuncao(funcionario.getFuncao());
            funcionarioNovo.setTelefone(funcionario.getTelefone());
            funcionarioNovo = resFunc.save(funcionarioNovo);
            setarEndereco(funcionarioNovo.getId(), enderecoId);
        }else{
            funcionarioNovo = null;
        }
        return funcionarioNovo;
    }

    public void deletarFuncionario(Long id){
        resFunc.deleteById(id);
    }

    public String setarEndereco(Long id, Long enderecoId) {
        Funcionario funcionario = resFunc.findById(id).get();
        Endereco endereco = funcionario.getEndereco();
        try{
            if (endereco == null) {
                funcionario.setEndereco(resEndereco.findById(enderecoId).get());
                resFunc.flush();
                return "Endereco setado!";
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }
    public String removerEndereco(Long id){
        Funcionario funcionario = resFunc.findById(id).get();
        try{
            funcionario.setEndereco(null);
            resFunc.flush();
            return "Endereco removido!";
        }catch (Exception e){
            return null;
        }
    }
    public String setarSetor(Long id, Long setorId){
        Funcionario funcionario = resFunc.findById(id).get();
        SetorDeposito setor = funcionario.getSetor();
        try{
            if(setor == null){
                funcionario.setSetor(resSetor.findById(setorId).get());
                resFunc.flush();
                return "Setor adicionado!";
            }
            return null;
        }catch (Exception e){
            return null;
        }
    }

    public String removerSetor(Long id){
        Funcionario funcionario = resFunc.findById(id).get();
        try{
            funcionario.setSetor(null);
            resFunc.flush();
            return "Setor removido!";
        }catch (Exception e){
            return null;
        }
    }

    public List<String> findAllFuncEnd(){
        return resFunc.findAllFuncEnd();
    }

    public List<String> findAllFuncSetor(){
        return resFunc.findAllFuncSetor();
    }
    public List<Funcionario> findAllFuncionarios(){
        return resFunc.findAll();
    }

    public Funcionario findFuncionarioById(Long id){
        Optional<Funcionario> f = resFunc.findById(id);
        return f.orElse(null);
    }


    public List<String> findTelFromFunc(Long id){
        return resFunc.findTelFromFunc(id);
    }
    public List<String> findEndFromFunc(Long id){
        return resFunc.findEndFromFunc(id);
    }
    public List<String> findSetorFromFunc(Long id){
        return resFunc.findSetorFromFunc(id);
    }
}
