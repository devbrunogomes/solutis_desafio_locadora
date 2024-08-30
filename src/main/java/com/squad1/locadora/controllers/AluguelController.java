package com.squad1.locadora.controllers;

import com.squad1.locadora.entities.aluguel.Aluguel;
import com.squad1.locadora.repositories.AluguelRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="alugueis")
public class AluguelController {
    @Autowired
    private AluguelRepository aluguelRepository;
    
    @GetMapping
    public List<Aluguel> findAll() {
        List<Aluguel> result = aluguelRepository.findAll();
        return result;
    }
    
    @GetMapping(value="/{id}")
    public Aluguel findById(@PathVariable Long id) {
        Aluguel result = aluguelRepository.findById(id).get();
        return result;
    }
}
