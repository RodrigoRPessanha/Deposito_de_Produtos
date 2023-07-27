package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllerView {
    @GetMapping(path = "/home")
    public String page(){
        return "layoutBase";
    }

    @GetMapping(path = "/Setor")
    public String Setor(){
        return "Setor";
    }
}
