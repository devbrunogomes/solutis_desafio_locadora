package com.squad1.locadora.controllers;

import com.squad1.locadora.entities.pessoa.Motorista;
import com.squad1.locadora.repositories.MotoristaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaRepository motoristaRepository;
    @Autowired
    private View error;

    @GetMapping
    public List<Motorista> findAll() {
        List<Motorista> result = motoristaRepository.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Motorista> findById(@PathVariable Long id) {
        Optional<Motorista> result = motoristaRepository.findById(id);

        if (result.isPresent()) {
            return ResponseEntity.ok(result.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null);
        }
    }

    @GetMapping(value = "/{id}/email")
    public ResponseEntity<String> obterEmailPorUmaId(@PathVariable Long id) {
        Optional<Motorista> motoristaSelecionado = motoristaRepository.findById(id);

        if (motoristaSelecionado.isPresent()) {
            return ResponseEntity.ok(motoristaSelecionado.get().getEmail());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Motorista com ID " + id + " não encontrado.");
        }
    }

    @PostMapping
    public ResponseEntity<Motorista> criarMotorista(@RequestBody Motorista motorista){

            if (motoristaRepository.existsById(motorista.getId())) {
                System.out.println("Id já existe");
                return ResponseEntity.badRequest().body(null);
            } else if (motoristaRepository.existsByEmail(motorista.getEmail())) {
                System.out.println("E-mail já registrado");
                return ResponseEntity.badRequest().body(null);
            } else if (motoristaRepository.existsByCpf(motorista.getCpf())) {
                System.out.println("CPF já existe");
                return ResponseEntity.badRequest().body(null);
            } else if (motoristaRepository.existsByNumeroCNH(motorista.getNumeroCNH())) {
                System.out.println("Número de CNH já existe");
                return ResponseEntity.badRequest().body(null);
            }
            
            
            Motorista novoMotorista = motoristaRepository.save(motorista);
            System.out.println("Motorista cadastrado com sucesso");
            return ResponseEntity.ok(novoMotorista);
        }

}
