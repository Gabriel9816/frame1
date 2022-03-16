package br.edu.ifms.frame1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @GetMapping("/add")
	public ModelAndView passParametersWithModelAndView() {
        ModelAndView mv = new ModelAndView("FormProd");
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView salvarUser(@Valid Prod prod, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) {
          return new ModelAndView("redirect:pageErro");
         }
         this.prodsservice.saveProd(prod);
        return new ModelAndView("redirect:/prods/");
    }
}