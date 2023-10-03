package br.edu.iff.bsi.deposito_de_produtos.service;

import br.edu.iff.bsi.deposito_de_produtos.model.Produto;
import br.edu.iff.bsi.deposito_de_produtos.model.SetorDeposito;
import br.edu.iff.bsi.deposito_de_produtos.repository.ProdutoRepository;
import br.edu.iff.bsi.deposito_de_produtos.repository.SetorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;
import java.util.Optional;

@Service
public class SetorService {

    @Autowired
    private SetorRepository resSetor;
    @Autowired
    private ProdutoRepository resProduto;
    public String addSetor(@ModelAttribute SetorDeposito setor){
        SetorDeposito s = setor;
        try{
            setor.trim();
            s = resSetor.findById(resSetor.findByDescricao(setor.getDescricao())).get();
            return null;
        }catch (Exception e){
            return "Setor " + resSetor.save(setor).getDescricao() + " adicionado";
        }
    }
    public SetorDeposito updateSetor(Long id, String descricaoNova){
        SetorDeposito setor;
        Optional<SetorDeposito> s = resSetor.findById(id);
        if (s.isPresent()){
            setor = s.get();
            setor.setDescricao(descricaoNova);
            setor = resSetor.save(setor);
        }else{
            setor = null;
        }
        return setor;
    }

    public String addProdutos(Long setorId, Long produtoId){

        SetorDeposito setor = resSetor.findById(setorId).get();
        List<Produto> produtos = setor.getProdutos();
        try{
            if(produtos.contains(resProduto.findById(produtoId).get())){
                return null;
            }
            setor.addProdutos(resProduto.findById(produtoId).get());
            resSetor.flush();
            return "Produto adicionado!";
        }catch (Exception e){
            return null;
        }
    }
    public String removerProdutos(Long setorId, Long produtoId){
        SetorDeposito setor = resSetor.findById(setorId).get();
        List<Produto> produtos = setor.getProdutos();
        try{
            if(!produtos.contains(resProduto.findById(produtoId).get())){
                return null;
            }
            setor.removerProduto(resProduto.findById(produtoId).get());
            resSetor.flush();
            return "Produto "+ produtoId + " do setor "+ setorId + " deletado!";
        }catch (Exception e){
            return null;
        }
    }


    public void deletarSetor(String descricao){
        resSetor.deleteById(resSetor.findByDescricao(descricao));
    }
    public void deletarSetor(Long id){
        resSetor.deleteById(id);
    }
    public List<SetorDeposito> findAllSetores(){
        return resSetor.findAll();
    }

    public List<String> findProdutoBySetorId(Long idDeposito){
        return resSetor.findProdutoBySetorId(idDeposito);
    }

    public SetorDeposito findSetorById(Long id){
        Optional<SetorDeposito> s = resSetor.findById(id);
        return s.orElse(null);
    }
}
