package br.edu.iff.bsi.deposito_de_produtos.service;

import br.edu.iff.bsi.deposito_de_produtos.model.Deposito;
import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.repository.DepositoRepository;
import br.edu.iff.bsi.deposito_de_produtos.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class DepositoService {

    @Autowired
    private DepositoRepository resDeposito;
    @Autowired
    private SetorRepository resSetor;
    public String addDeposito(@ModelAttribute Deposito deposito){
        Deposito d = deposito;
        try{
            deposito.trim();
            d = resDeposito.findById(resDeposito.findByDescricao(deposito.getDescricao())).get();
            return null;
        } catch (Exception e){
            return "Deposito " + resDeposito.save(deposito).getDescricao() + " adicionado";
        }
    }

    public String addSetores(Long depositoId, Long setor_Id){

        Deposito deposito = resDeposito.findById(depositoId).get();
        List<SetorDeposito> depositos = deposito.getSetores();
        try{
            if(depositos.contains(resSetor.findById(setor_Id).get())){
                return null;
            }
            deposito.addSetores(resSetor.findById(setor_Id).get());
            resDeposito.flush();
            return "Setor adicionado!";
        }catch (Exception e){
            return null;
        }

    }
    public Deposito updateDeposito(String descricaoAtual, String descricaoNova){
        Deposito setor;
        Optional<Deposito> s = resDeposito.findById(resDeposito.findByDescricao(descricaoAtual));
        if (s.isPresent()){
            setor = s.get();
            setor.setDescricao(descricaoNova);
            setor = resDeposito.save(setor);
        }else{
            setor = null;
        }
        return setor;
    }
    public Deposito updateDeposito(Long id, String descricaoNova){
        Deposito setor;
        Optional<Deposito> s = resDeposito.findById(id);
        if (s.isPresent()){
            setor = s.get();
            setor.setDescricao(descricaoNova);
            setor = resDeposito.save(setor);
        }else{
            setor = null;
        }
        return setor;
    }
    public void deletarDeposito(String descricao){
        resDeposito.deleteById(resDeposito.findByDescricao(descricao));
    }
    public void deletarDeposito(Long id){
        resDeposito.deleteById(id);
    }
    public List<Deposito> findAllDepositos(){
        return resDeposito.findAll();
    }
}
