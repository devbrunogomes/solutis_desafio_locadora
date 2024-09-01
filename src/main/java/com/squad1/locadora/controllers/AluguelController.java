package com.squad1.locadora.controllers;

import com.squad1.locadora.DTO.AluguelDTO;
import com.squad1.locadora.entities.aluguel.Aluguel;
import com.squad1.locadora.entities.aluguel.ApoliceSeguro;
import com.squad1.locadora.entities.aluguel.MetodoPagamento;
import com.squad1.locadora.entities.carro.Carro;
import com.squad1.locadora.entities.pessoa.Motorista;
import com.squad1.locadora.repositories.AluguelRepository;
import com.squad1.locadora.repositories.ApoliceRepository;
import com.squad1.locadora.repositories.CarroRepository;
import com.squad1.locadora.repositories.MotoristaRepository;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "alugueis")
public class AluguelController {

    @Autowired
    private AluguelRepository aluguelRepository;

    @Autowired
    private ApoliceRepository apoliceRepository;

    @Autowired
    private CarroRepository carroRepository;

    private MotoristaRepository MotoristaRepository;

    @GetMapping
    public List<Aluguel> findAll() {
        List<Aluguel> result = aluguelRepository.findAll();
        return result;
    }

    @GetMapping(value = "/{id}")
    public Aluguel findById(@PathVariable Long id) {
        Aluguel result = aluguelRepository.findById(id).get();
        return result;
    }

    @PostMapping
    public ResponseEntity<?> inserirAluguel(@RequestBody AluguelDTO aluguelDTO) {
        //Pegar os novos objetos de Apolice, Carro e Motorista
        Optional<ApoliceSeguro> apoliceASerIncluida = apoliceRepository.findById(aluguelDTO.getApoliceId());
        Optional<Carro> carroASerIncluido = carroRepository.findById(aluguelDTO.getCarroId());
        Optional<Motorista> motoristaASerIncluido = MotoristaRepository.findById(aluguelDTO.getMotoristaId());

        Calendar dataLocal = Calendar.getInstance();

        //Pra calcular o valor total
        ApoliceSeguro apolicePraPegarOValor = apoliceASerIncluida.get();
        float valorApolice = apolicePraPegarOValor.getValorFranquia();

        Carro carroPraPegarOValor = carroASerIncluido.get();
        float valorDiariaCarro = carroPraPegarOValor.getValorDiaria();

        long diasAlugados = calcularDiferencaEmDias(aluguelDTO.getDataEntrega(), aluguelDTO.getDataDevolucao());

        float valorTotal = valorApolice + (valorDiariaCarro * diasAlugados);

        //Nova instancia de aluguel com o objeto retornado pelos metodos findById
        Aluguel novoAluguel = new Aluguel(
                Long.MIN_VALUE,
                dataLocal,
                aluguelDTO.getDataEntrega(),
                aluguelDTO.getDataDevolucao(),
                valorTotal,
                apoliceASerIncluida.get(),
                carroASerIncluido.get(),
                motoristaASerIncluido.get(),
                aluguelDTO.getMetodoPagamento());
        
        //Inserir nova instancia no banco de dados
        Aluguel aluguelASerInserido = aluguelRepository.save(novoAluguel);
        return ResponseEntity.ok(aluguelASerInserido);

    }

     public static long calcularDiferencaEmDias(Date dataInicial, Date dataFinal) {
        long milissegundosInicial = dataInicial.getTime();
        long milissegundosFinal = dataFinal.getTime();
        
        long diferencaMilissegundos = milissegundosFinal - milissegundosInicial;
        
        // Converter a diferença em milissegundos para dias
        return TimeUnit.MILLISECONDS.toDays(diferencaMilissegundos);
    }

}
