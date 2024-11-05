package br.com.duxusdesafio.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/integrante")
public class IntegranteController {

    @GetMapping
    public String teste(){
        return "teste Integrante";
    }
}
