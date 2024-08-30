package com.squad1.locadora.controllers;

import com.squad1.locadora.entities.pessoa.Motorista;
import com.squad1.locadora.repositories.MotoristaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Motorista> criarMotorista(@RequestBody Motorista motorista){
        if(motoristaRepository.existsById(motorista.getId())){
            return ResponseEntity.badRequest().body(null);
        }

        Motorista novoMotorista = motoristaRepository.save(motorista);
        return ResponseEntity.ok(novoMotorista);
    }
}
