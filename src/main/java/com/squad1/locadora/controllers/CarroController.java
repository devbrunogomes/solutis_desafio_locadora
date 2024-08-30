package com.squad1.locadora.controllers;

import com.squad1.locadora.DTO.CarroDisponivelDTO;
import com.squad1.locadora.entities.carro.Carro;
import com.squad1.locadora.entities.pessoa.Motorista;
import com.squad1.locadora.repositories.CarroRepository;
import java.util.ArrayList;
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

    //Listar todos os carros
    @GetMapping
    public List<Carro> findAll() {
        List<Carro> carros = carroRepository.findAll();
        //Para forçar o carregamento dos acessorios
        carros.forEach(carro -> {
            System.out.println("Carro ID: " + carro.getId() + " - Acessorios: " + carro.getAcessorios());
        });
        return carros;
    }

    //Listar Carros por ID
    @GetMapping(value = "/{id}")
    public Carro findById(@PathVariable Long id) {
        Carro result = carroRepository.findByIdWithAcessorios(id).orElse(null);

        if (result != null) {
            System.out.println("Acessorios: " + result.getAcessorios());
        }

        return result;
    }

    //Listar carros disponiveis
    @GetMapping(value = "/disponiveis")
    public List<CarroDisponivelDTO> procurarCarrosDisponiveis() {
        List<Carro> todosOsCarros = this.findAll();
        List<CarroDisponivelDTO> carrosDisponiveis = new ArrayList<>();
        
        //Nova instancia de DTO para personalizar a exibição
        for (Carro carro : todosOsCarros) {
            if (!carro.isReserva()) {
                CarroDisponivelDTO dto = new CarroDisponivelDTO(
                        carro.getId(),
                        carro.getValorDiaria(),
                        carro.getModeloCarro(),
                        carro.getAcessorios(),
                        carro.isReserva()
                );
                carrosDisponiveis.add(dto);
            }
        }

        return carrosDisponiveis;
    }
}
