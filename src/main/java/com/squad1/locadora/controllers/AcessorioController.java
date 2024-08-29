
package com.squad1.locadora.controllers;

import com.squad1.locadora.entities.carro.Acessorio;
import com.squad1.locadora.repositories.AcessorioRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="acessorios")
public class AcessorioController {
    @Autowired
    private AcessorioRepository acessorioRepository;
    
    @GetMapping
    public List<Acessorio> findAll() {
        List<Acessorio> result = acessorioRepository.findAll();
        return result;
    }
}
