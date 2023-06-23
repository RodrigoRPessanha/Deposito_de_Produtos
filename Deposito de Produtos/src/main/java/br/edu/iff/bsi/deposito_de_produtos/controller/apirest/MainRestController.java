package br.edu.iff.bsi.controller.apirest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class MainRestController {
    @GetMapping
    @ResponseBody
    public String intial(){
        return "Olá, bem vindo a API do meu APP";
    }
//    @GetMapping("/{id}")
//    public String intial(@PathVariable("id") int id){
//        return "Olá mundo ->" + id;
//    }
}
