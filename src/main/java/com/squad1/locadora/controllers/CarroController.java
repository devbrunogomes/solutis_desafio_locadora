package com.squad1.locadora.controllers;

import com.squad1.locadora.DTO.CarroDisponivelDTO;
import com.squad1.locadora.entities.carro.Carro;
import com.squad1.locadora.entities.pessoa.Motorista;
import com.squad1.locadora.repositories.CarroRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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

    @PatchMapping(value = "/reservar-carro/{id}")
    public ResponseEntity<String> reservarCarroPorId(@PathVariable Long id) {
        Carro carroASerReservado = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        //Verifica se já está reservado
        if (carroASerReservado.isReserva()) {
            //Se já estiver, erro 409
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("O carro " + carroASerReservado.getModeloCarro().getNomeFabricante().getNome() + " "
                            + carroASerReservado.getModeloCarro().getDescricao()
                            + " " + carroASerReservado.getPlaca() + " já está reservado");
        } else {
            // Se estiver disponivel, reserva o carro
            carroASerReservado.setReserva(true);
            carroRepository.save(carroASerReservado);

            return ResponseEntity.ok("O carro " + carroASerReservado.getModeloCarro().getNomeFabricante().getNome() + " "
                    + carroASerReservado.getModeloCarro().getDescricao()
                    + " " + carroASerReservado.getPlaca() + " foi reservado com sucesso!");
        }
    }

    @PatchMapping(value = "/devolver-carro/{id}")
    public ResponseEntity<String> devolverCarroPorId(@PathVariable Long id) {
        Carro carroASerDevolvido = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));

        // Verifica se o carro está reservado
        if (!carroASerDevolvido.isReserva()) {
            // Se não estiver reservado, erro 409
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("O carro " + carroASerDevolvido.getModeloCarro().getNomeFabricante().getNome() + " "
                            + carroASerDevolvido.getModeloCarro().getDescricao()
                            + " " + carroASerDevolvido.getPlaca() + " já está disponível.");
        } else {
            // Se estiver reservado, devolve o carro e marca como disponível
            carroASerDevolvido.setReserva(false);
            carroRepository.save(carroASerDevolvido);

            return ResponseEntity.ok("O carro " + carroASerDevolvido.getModeloCarro().getNomeFabricante().getNome() + " "
                    + carroASerDevolvido.getModeloCarro().getDescricao()
                    + " " + carroASerDevolvido.getPlaca() + " foi devolvido com sucesso!");
        }
    }

}
