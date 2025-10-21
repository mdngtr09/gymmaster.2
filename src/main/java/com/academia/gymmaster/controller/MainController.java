package com.academia.gymmaster.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index"; // templates/index.html
    }

    @GetMapping("/alunos")
    public String alunos() {
        return "aluno"; // templates/aluno.html
    }

    @GetMapping("/planos")
    public String planos() {
        return "planos"; // templates/planos.html
    }

    @GetMapping("/exercicios")
    public String exercicios() {
        return "exercicios"; // templates/exercicios.html
    }

    @GetMapping("/divisoes")
    public String divisoes() {
        return "divisao"; // templates/divisao.html
    }
}
