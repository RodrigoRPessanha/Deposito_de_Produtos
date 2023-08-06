package br.edu.iff.bsi.deposito_de_produtos.controller.apirest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/deposito")
public class DepositoRestController {

    @GetMapping
    @ResponseBody
    public String getDeposito(){
        return "Depositos";
    }

    @PostMapping
    @ResponseBody
    public String addDeposito(){
        return "Deposito criado";
    }
    @PutMapping(path = "/{id}")
    @ResponseBody
    public String updateDeposito(@PathVariable("id") String id){
        return "Deposito atualizado";
    }
    @DeleteMapping(path = "/{id}")
    @ResponseBody
    public String deleteDeposito(@PathVariable("id") String id){
        return "Deposito deletado";
    }
}
