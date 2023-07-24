package br.edu.iff.bsi.deposito_de_produtos.service;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class SetorService {

    @Autowired
    private SetorRepository res;
    public String addSetor(@ModelAttribute SetorDeposito setor){
        setor.trim();
        SetorDeposito s = res.findById(res.findByDescricao(setor.getDescricao())).get();
        return (setor.getDescricao() == s.getDescricao()) ? null : "Setor " + res.save(setor).getDescricao() + " adicionado";
    }
    public SetorDeposito updateSetor(String descricaoAtual, String descricaoNova){
        SetorDeposito setor;
        Optional<SetorDeposito> s = res.findById(res.findByDescricao(descricaoAtual));
        if (s.isPresent()){
            setor = s.get();
            setor.setDescricao(descricaoNova);
            setor = res.save(setor);
        }else{
            setor = null;
        }
        return setor;
    }
    public void deletarSetor(String descricao){
        res.deleteById(res.findByDescricao(descricao));
    }
    public List<SetorDeposito> findAllSetores(){
        return res.findAll();
    }
}
