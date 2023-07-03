package br.edu.iff.bsi.deposito_de_produtos.controller.apirest;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(path = "/api")
public class MainRestController {

    @GetMapping
    @ResponseBody
    public String intial(){
        return "Olá bem vindo a API do me APP";
    }
    @GetMapping("/{id}")
    public String intial(@PathVariable("id") int id){
        return "Olá mundo ->" + id;
    }
    @GetMapping("/{id}")
    public String intial(@PathVariable("id") int id, @RequestParam("nome") String nome){
        return "Olá mundo ->" + id + "->" + nome;
    }
    @PostMapping("/new")
    @ResponseBody
    public String addItem(@RequestParam Map<String, String> allParams){
        return "Os parâmetros passados foram " + allParams.entrySet();
    }
}
