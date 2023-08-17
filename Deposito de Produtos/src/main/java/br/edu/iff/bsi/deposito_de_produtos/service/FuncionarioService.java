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

import java.util.Collection;
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
    public String addFuncionario(@ModelAttribute Funcionario funcionario){
        try{
            resFunc.findById(funcionario.getId()).get();
            return null;
        }catch(Exception e){
            return "Funcionario " + resFunc.save(funcionario).getNome() + " adicionado";
        }
    }
    public Funcionario updateFuncionario(@ModelAttribute Funcionario funcionario){
        Funcionario funcionarioNovo;
        Optional<Funcionario> p = resFunc.findById(funcionario.getId());
        if (p.isPresent()){
            funcionarioNovo = p.get();
            funcionarioNovo.setNome(funcionario.getNome());
            funcionarioNovo.setCpf(funcionario.getCpf());
            funcionarioNovo.setEmail(funcionario.getEmail());
            funcionarioNovo.setEndereco(funcionario.getEndereco());
            funcionarioNovo.setFuncao(funcionario.getFuncao());
            funcionarioNovo.setTelefone(funcionario.getTelefone());
            funcionarioNovo = resFunc.save(funcionarioNovo);
        }else{
            funcionarioNovo = null;
        }
        return funcionarioNovo;
    }
    public Funcionario updateFuncionario(Long id, @ModelAttribute Funcionario funcionario){
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
        }else{
            funcionarioNovo = null;
        }
        return funcionarioNovo;
    }

    public void deletarFuncionario(String cpf){
        resFunc.deleteById(resFunc.findByCPF(cpf));
    }
    public void deletarFuncionario(Long id){
        resFunc.deleteById(id);
    }


    public String addTelefone(Long id, String tel){
        Funcionario funcionario = resFunc.findById(id).get();
        Collection<String> funcionarios = funcionario.getTelefone();
        try{
            if(funcionarios.contains(tel.trim())){
                return null;
            }
            funcionario.addTelefone(tel.trim());
            resFunc.flush();
            return "Telefone adicionado!";
        }catch (Exception e){
            return null;
        }
    }
    public String removerTelefone(Long id, String tel){
        Funcionario funcionario = resFunc.findById(id).get();
        Collection<String> funcionarios = funcionario.getTelefone();
        try{
            if(!funcionarios.contains(tel.trim())){
                return null;
            }
            funcionario.removeTelefone(tel.trim());
            resFunc.flush();
            return "Telefone removido!";
        }catch (Exception e){
            return null;
        }
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
            return "Endereco removido!";
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
}
