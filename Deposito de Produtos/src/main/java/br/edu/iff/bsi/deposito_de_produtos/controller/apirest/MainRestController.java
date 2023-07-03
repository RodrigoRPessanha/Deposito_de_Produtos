package br.edu.iff.bsi.deposito_de_produtos.controller.apirest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
//    @GetMapping("/{id}")
//    public String intial(@PathVariable("id") int id, @RequestParam("nome") String nome){
//        return "Olá mundo ->" + id + "->" + nome;
//    }
    @PostMapping("/new")
    @ResponseBody
    public String addItem(@RequestParam Map<String, String> allParams){
        return "Os parâmetros passados foram " + allParams.entrySet();
    }

    @PostMapping("/new/users")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, String> registerUser(@RequestBody Map<String, String> userMap){
        try{
            if(userMap.get("userName") == null || userMap.get("password") == null){
                throw new NullPointerException("Erro");
            }
            System.out.println("User ID: " + userMap.get("userName"));
            System.out.println("User ID: " + userMap.get("password"));
            return userMap;
        }
        catch(Exception e){
            String errorMessage = "Erro: userName ou password são nulos";
            Map<String, String> errorMap = new HashMap<>();
            errorMap.put("error", errorMessage);
            return errorMap;
        }
    }
@GetMapping("/get/users/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") String id){
        if(id.equals("1")){
            return ResponseEntity.ok().header("Content-Type", "application/json").body("Usuário 1");
        } else{
            return ResponseEntity.notFound().header("Content-Type", "application/json").build();
        }
    }

    
}
