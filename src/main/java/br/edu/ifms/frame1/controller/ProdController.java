package br.edu.ifms.frame1.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifms.frame1.model.Prod;
import br.edu.ifms.frame1.model.ProdNotFoundException;
import br.edu.ifms.frame1.service.ProdService;

@Controller
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
        ModelAndView mv = new ModelAndView("ListarTodosProds");   
        List<Prod> prods = this.prodsservice.getProds();
         mv.addObject("prodsList", prods);
         return mv;
    }

    @GetMapping("/add")
	public ModelAndView passParametersWithModelAndView() {
        ModelAndView mv = new ModelAndView("FormProd");
        return mv;
    }

    @GetMapping("/erro")
	public ModelAndView Erro() {
        ModelAndView mv = new ModelAndView("pageErro");
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView salvarProd(@Valid Prod prod, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) {
          return new ModelAndView("redirect:/prods/erro");
         }
         this.prodsservice.saveProd(prod);
        return new ModelAndView("redirect:/prods/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteprod(@PathVariable ("id")UUID id){
        Optional<Prod> proddel = prodsservice.findById(id);
        if(!proddel.isPresent()){
            return new ModelAndView("redirect:/prods/erro");
        } 
        prodsservice.delete(proddel.get());
        return new ModelAndView("redirect:/prods/");
  }
  @GetMapping("/edit/{id}")
  public String editProd(@PathVariable("id") UUID prodID, Model model, RedirectAttributes ra){
      try{
        Prod prod = prodsservice.get(prodID);
          model.addAttribute("prod", prod);
         model.addAttribute("Titulo da pagina", "Edit user" + prod.getNome());
          return "editprod";
      } catch (ProdNotFoundException e) {
          ra.addFlashAttribute("message", "Usuario editado");
          return "redirect:/prods/";
      }
  }

  @PostMapping("/update/{id}")
  public String updatePode(@PathVariable("id") UUID prodID, @Valid Prod prod, BindingResult result, Model model) {
      if (result.hasErrors()) {
             prod.setId(prodID);
          return "editprod";
      }
      
      this.prodsservice.saveProd(prod);

      return "redirect:/prods/";
  }
}