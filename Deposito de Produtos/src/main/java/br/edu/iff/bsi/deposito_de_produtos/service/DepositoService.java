package br.edu.iff.bsi.deposito_de_produtos.service;

import br.edu.iff.bsi.deposito_de_produtos.model.Deposito;
import br.edu.iff.bsi.deposito_de_produtos.repository.DepositoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository res;
    public String addDeposito(@ModelAttribute Deposito deposito){
        Deposito d = deposito;
        try{
            deposito.trim();
            d = res.findById(res.findByDescricao(deposito.getDescricao())).get();
            return null;
        } catch (Exception e){
            return "Deposito " + res.save(deposito).getDescricao() + " adicionado";
        }
    }
    public Deposito updateDeposito(String descricaoAtual, String descricaoNova){
        Deposito setor;
        Optional<Deposito> s = res.findById(res.findByDescricao(descricaoAtual));
        if (s.isPresent()){
            setor = s.get();
            setor.setDescricao(descricaoNova);
            setor = res.save(setor);
        }else{
            setor = null;
        }
        return setor;
    }

    public void deletarDeposito(String descricao){
        res.deleteById(res.findByDescricao(descricao));
    }
    public List<Deposito> findAllDepositos(){
        return res.findAll();
    }
}
