package br.edu.iff.bsi.deposito_de_produtos.service;

import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
public class SetorService {
    @Autowired
    private SetorRepository res;
    public SetorDeposito salvarSetor(@ModelAttribute SetorDeposito setor){
        SetorDeposito s = res.save(setor);
        return s;
    }
    public SetorDeposito updateSetor(String descricaoAtual, String descricaoNova){
        Optional<SetorDeposito> s = res.findById(res.findByDescricao(descricaoAtual));
        s.setDescricao(descricaoNova);
        res.save(s);
        return s;
    }
    public void deletarSetor(@ModelAttribute SetorDeposito setor){
        res.delete(setor);
    }
    public List<SetorDeposito> findAllSetores(){
        return res.findAll();
    }
}
