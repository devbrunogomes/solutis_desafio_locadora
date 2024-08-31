package com.squad1.locadora.controllers;

import com.squad1.locadora.DTO.CarroDTO;
import com.squad1.locadora.DTO.CarroDisponivelDTO;
import com.squad1.locadora.DTO.CategoriaDTO;
import com.squad1.locadora.entities.carro.Acessorio;
import com.squad1.locadora.entities.carro.Carro;
import com.squad1.locadora.entities.carro.Categoria;
import com.squad1.locadora.entities.carro.ModeloCarro;
import com.squad1.locadora.repositories.AcessorioRepository;
import com.squad1.locadora.repositories.CarroRepository;

import java.util.*;
import java.util.stream.Collectors;

import com.squad1.locadora.repositories.ModeloCarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping(value = "carros")
public class CarroController {


    @Autowired
    private ModeloCarroRepository modeloCarroRepository;

    @Autowired
    private AcessorioRepository acessorioRepository;

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

    @GetMapping(value = "/categoria/{categoria}")
   public List<CategoriaDTO> buscarPorCategoria(@PathVariable String categoria){
        Categoria categoriaEnum;

        try {
            categoriaEnum = Categoria.valueOf(categoria.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Categoria inválida");
        }

        List<ModeloCarro> carrosPorCategoria = modeloCarroRepository.findByCategoria(categoriaEnum);
            List<CategoriaDTO> lista = new ArrayList<>();

            for (ModeloCarro carros : carrosPorCategoria){
                CategoriaDTO dto = new CategoriaDTO(
                carros.getId(),
                carros.getDescricao(),
                carros.getNomeFabricante(),
                carros.getCategoria()
                );
                lista.add(dto);
    }
            return lista;
    }


    @GetMapping(value = "/acessoriosid/{id}")
    public List<Carro> buscaCarrosPorIdAcessorio(@PathVariable Long id) {
        List<Carro> carrosComAcessorio = new ArrayList<>();


        List<Carro> todosCarros = carroRepository.findAll();


       for (Carro carro : todosCarros){
            for (Acessorio acessorio : carro.getAcessorios()){
                if(acessorio.getId().equals(id)){
                    carrosComAcessorio.add(carro);
                    break;
                }
            }
       }
        return carrosComAcessorio;
    }



}
