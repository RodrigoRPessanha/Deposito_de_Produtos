package br.edu.iff.bsi.deposito_de_produtos.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home")
public class MainControllerView {
    @GetMapping()
    public String page(){
        return "layoutBase";
    }

}
