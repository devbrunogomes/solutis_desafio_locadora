package com.squad1.locadora.controllers;

import com.squad1.locadora.entities.carro.Carro;
import com.squad1.locadora.entities.pessoa.Motorista;
import com.squad1.locadora.repositories.CarroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "carros")
public class CarroController {

    @Autowired
    private CarroRepository carroRepository;

    @GetMapping
    public List<Carro> findAll() {
        List<Carro> carros = carroRepository.findAll();
        //Para forçar o carregamento dos acessorios
        carros.forEach(carro -> {
            System.out.println("Carro ID: " + carro.getId() + " - Acessorios: " + carro.getAcessorios());
        });
        return carros;
    }

    @GetMapping(value = "/{id}")
    public Carro findById(@PathVariable Long id) {
        Carro result = carroRepository.findByIdWithAcessorios(id).orElse(null);

        if (result != null) {
            System.out.println("Acessorios: " + result.getAcessorios());
        }

        return result;
    }
}
