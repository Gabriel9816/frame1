package br.edu.ifms.frame1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

public class UserController {
    
    @RequestMapping("/listar")
    public String getUser() {
        return "Usuario localizado";
    }
}
