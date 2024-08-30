package com.squad1.locadora.controllers;

import com.squad1.locadora.entities.pessoa.Motorista;
import com.squad1.locadora.repositories.MotoristaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="motoristas")
public class MotoristaController {
    @Autowired
    private MotoristaRepository motoristaRepository;
    
    @GetMapping
    public List<Motorista> findAll() {
        List<Motorista> result = motoristaRepository.findAll();
        return result;
    }
    
    @GetMapping(value="/{id}")
    public Motorista findById(@PathVariable Long id){
        Motorista result = motoristaRepository.findById(id).get();
        return result;
    }
}
