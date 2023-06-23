package br.edu.iff.bsi.main.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/home")
public class MainControllerView {
    @GetMapping()
    public String page(){
        return "index";
    }

//    @GetMapping(("/{id}"))
//    public String page(@PathVariable("id") int id){
//        return "index";
//    }
}
