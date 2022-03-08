package br.edu.ifms.frame1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.edu.ifms.frame1.model.Prod;
import br.edu.ifms.frame1.service.ProdService;

@RestController
@RequestMapping("/prods")

public class ProdController {
    @Autowired
    private ProdService prodsservice;    

    @RequestMapping("/listar")
    public String getProd() {
        return "Produtos localizado";
    }
     @GetMapping("/")
     public ModelAndView listarTodosProds() {
        ModelAndView mv2 = new ModelAndView("ListarTodosProds");   
        List<Prod> prods = this.prodsservice.getProds();
         mv2.addObject("prodsList", prods);
         return mv2;
    }
}

