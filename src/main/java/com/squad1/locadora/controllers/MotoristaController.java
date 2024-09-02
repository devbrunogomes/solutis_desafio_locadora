package com.squad1.locadora.controllers;

import com.squad1.locadora.entities.pessoa.Motorista;
import com.squad1.locadora.repositories.MotoristaRepository;
import java.util.List;

import com.squad1.locadora.response.ErrorResponse;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.View;


@RestController
@RequestMapping(value="motoristas")
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

    @GetMapping(value="/{id}")
    public Motorista findById(@PathVariable Long id){
        Motorista result = motoristaRepository.findById(id).get();
        return result;
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
    public ResponseEntity<?> criarMotorista(@RequestBody Motorista motorista) {
        if (motoristaRepository.existsByEmail(motorista.getEmail())) {
            String errorMessage = "E-mail já registrado";
            System.out.println(errorMessage);
            return ResponseEntity.badRequest().body(new ErrorResponse(errorMessage));
        } else if (motoristaRepository.existsByCpf(motorista.getCpf())) {
            String errorMessage = "CPF já existe";
            System.out.println(errorMessage);
            return ResponseEntity.badRequest().body(new ErrorResponse(errorMessage));
        } else if (motoristaRepository.existsByNumeroCNH(motorista.getNumeroCNH())) {
            String errorMessage = "Número de CNH já existe";
            System.out.println(errorMessage);
            return ResponseEntity.badRequest().body(new ErrorResponse(errorMessage));
        }

        Motorista novoMotorista = motoristaRepository.save(motorista);
        System.out.println("Motorista cadastrado com sucesso");
        return ResponseEntity.ok(novoMotorista);
    }

}


