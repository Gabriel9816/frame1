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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.ifms.frame1.model.User;
import br.edu.ifms.frame1.model.UserNotFoundException;
import br.edu.ifms.frame1.service.UserService;

@Controller
@RequestMapping("/users")

public class UserController {
    
    @Autowired
    private UserService userservice;    

    @RequestMapping("/listar")
    public String getUser() {
        return "Usuario localizado";
    }
    @GetMapping("/")
    public ModelAndView listarTodos() {
       ModelAndView mv = new ModelAndView("ListarTodos");   
       List<User> users = this.userservice.getUsers();
        mv.addObject("usersList", users);
        return mv;
    }

    @GetMapping("/add")
	public ModelAndView passParametersWithModelAndView() {
        ModelAndView mv = new ModelAndView("Formulario");
        return mv;
    }

    @GetMapping("/erro")
	public ModelAndView Erro() {
        ModelAndView mv = new ModelAndView("pageErro");
        return mv;
    }

    @PostMapping("/save")
    public ModelAndView salvarUser(@Valid User user, BindingResult result, RedirectAttributes redirect){
        if (result.hasErrors()) {
          return new ModelAndView("redirect:/users/erro");
         }
         this.userservice.saveUser(user);
        return new ModelAndView("redirect:/users/");
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deleteuser(@PathVariable ("id")UUID id){
        Optional<User> userop = userservice.findById(id);
        if(!userop.isPresent()){
            return new ModelAndView("redirect:/users/erro");
        } 
        userservice.delete(userop.get());
        return new ModelAndView("redirect:/users/");
    }
    
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") UUID userID, Model model, RedirectAttributes ra){
        try{
            User user = userservice.get(userID);
            model.addAttribute("user", user);
           model.addAttribute("Titulo da pagina", "Edit user" + user.getNome());
            return "edituser";
        } catch (UserNotFoundException e) {
            ra.addFlashAttribute("message", "Usuario editado");
            return "redirect:/users/";
        }
    }

    @PostMapping("/adduser")
    public String addUser(@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "edituser";
        }
        
        userservice.saveUser(user);
        return "redirect:/users/";
    }

    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") UUID userID,@Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(userID);
            return "edituser";
        }
        userservice.saveUser(user);
        return "redirect:/users/";
    }
}