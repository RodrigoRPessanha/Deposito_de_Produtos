package br.edu.iff.bsi.deposito_de_produtos.service;

import br.edu.iff.bsi.deposito_de_produtos.model.Endereco;
import br.edu.iff.bsi.deposito_de_produtos.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository res;
    public Endereco addEndereco(@ModelAttribute Endereco endereco){
        endereco.trim();
        return res.save(endereco);
    }
    public Endereco updateEndereco(@ModelAttribute Endereco endereco){
        Endereco enderecoNovo;
        Optional<Endereco> e = res.findById(endereco.getId());
        if (e.isPresent()){
            enderecoNovo = e.get();
            enderecoNovo.setRua(endereco.getRua());
            enderecoNovo.setNumero(endereco.getNumero());
            enderecoNovo.setBairro(endereco.getBairro());
            enderecoNovo.setCidade(endereco.getCidade());
            enderecoNovo.setEstado(endereco.getEstado());
            enderecoNovo.setCep(endereco.getCep());
            enderecoNovo = res.save(enderecoNovo);
        }else{
            enderecoNovo = null;
        }
        return enderecoNovo;
    }
    public Endereco updateEndereco(Long id, Endereco endereco){
        Endereco enderecoNovo;
        Optional<Endereco> e = res.findById(id);
        if (e.isPresent()){
            enderecoNovo = e.get();
            enderecoNovo.setRua(endereco.getRua());
            enderecoNovo.setNumero(endereco.getNumero());
            enderecoNovo.setBairro(endereco.getBairro());
            enderecoNovo.setCidade(endereco.getCidade());
            enderecoNovo.setEstado(endereco.getEstado());
            enderecoNovo.setCep(endereco.getCep());
            enderecoNovo = res.save(enderecoNovo);
        }else{
            enderecoNovo = null;
        }
        return enderecoNovo;
    }

    public void deletarEndereco(@ModelAttribute Endereco endereco){
        res.delete(endereco);
    }
    public void deletarEndereco(Long id){
        res.deleteById(id);
    }
    public List<Endereco> findAllEnderecos(){
        return res.findAll();
    }
}
