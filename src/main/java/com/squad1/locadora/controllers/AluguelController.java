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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.squad1.locadora.response.ErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

        private final AluguelRepository aluguelRepository;
        private final CarroRepository carroRepository;
        private final MotoristaRepository motoristaRepository;
        private final ApoliceRepository apoliceRepository;


        public AluguelController(
                AluguelRepository aluguelRepository,
                CarroRepository carroRepository,
                MotoristaRepository motoristaRepository,
                ApoliceRepository apoliceRepository) {
            this.aluguelRepository = aluguelRepository;
            this.carroRepository = carroRepository;
            this.motoristaRepository = motoristaRepository;
            this.apoliceRepository = apoliceRepository;
        }


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

        // Verificar se o ID do motorista foi fornecido
        if (aluguelDTO.getMotoristaId() == null) {
            return ResponseEntity.badRequest().body("Erro: O ID do motorista é obrigatório para realizar o aluguel.");
        }

        // Verificar se o ID do carro foi fornecido
        if (aluguelDTO.getCarroId() == null) {
            return ResponseEntity.badRequest().body("Erro: O ID do carro é obrigatório para realizar o aluguel.");
        }

        // Pegar os novos objetos de Apólice, Carro e Motorista
        Optional<ApoliceSeguro> apoliceASerIncluida = Optional.empty();
        if (aluguelDTO.getApoliceId() != null) {
            apoliceASerIncluida = apoliceRepository.findById(aluguelDTO.getApoliceId());
        }

        Carro carroASerIncluido = carroRepository.findById(aluguelDTO.getCarroId())
                .orElse(null);
        Motorista motoristaASerIncluido = motoristaRepository.findById(aluguelDTO.getMotoristaId())
                .orElse(null);

        if (carroASerIncluido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Carro não encontrado"));
        }
        if (motoristaASerIncluido == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ErrorResponse("Motorista não encontrado"));
        }

        // Obter a data atual
        Calendar calendarAtual = Calendar.getInstance();
        Date dataAtual = calendarAtual.getTime();

        // Converter a dataPedido de Calendar para Date
        Date dataPedido = aluguelDTO.getDataPedido().getTime();

        // Verificar as datas
        if (dataPedido.before(dataAtual)) {
            return ResponseEntity.badRequest().body("Erro: A data do pedido não pode ser anterior à data atual.");
        }
        if (aluguelDTO.getDataEntrega().before(dataAtual)) {
            return ResponseEntity.badRequest().body("Erro: A data da entrega não pode ser anterior à data atual.");
        }
        if (aluguelDTO.getDataDevolucao().before(dataAtual)) {
            return ResponseEntity.badRequest().body("Erro: A data da devolução não pode ser anterior à data atual.");
        }

        // Calcular o valor total do aluguel
        float valorApolice = apoliceASerIncluida.map(ApoliceSeguro::getValorFranquia).orElse(0f);
        float valorDiariaCarro = carroASerIncluido.getValorDiaria();
        long diasAlugados = calcularDiferencaEmDias(aluguelDTO.getDataEntrega(), aluguelDTO.getDataDevolucao());
        float valorTotal = valorApolice + (valorDiariaCarro * diasAlugados);

        // Criar nova instância de aluguel com os objetos retornados pelos métodos findById
        Aluguel novoAluguel = new Aluguel(
                null,
                calendarAtual,
                aluguelDTO.getDataEntrega(),
                aluguelDTO.getDataDevolucao(),
                valorTotal,
                apoliceASerIncluida.orElse(null), // Inclui apólice se estiver presente
                carroASerIncluido,
                motoristaASerIncluido,
                aluguelDTO.getMetodoPagamento()
        );

        // Inserir nova instância no banco de dados
        aluguelRepository.save(novoAluguel);

        // Retornar uma mensagem de sucesso
        return ResponseEntity.ok("Reserva realizada com sucesso.");
    }

    // Método para calcular a diferença em dias entre duas datas
    public static long calcularDiferencaEmDias(Date dataInicial, Date dataFinal) {
        long milissegundosInicial = dataInicial.getTime();
        long milissegundosFinal = dataFinal.getTime();

        long diferencaMilissegundos = milissegundosFinal - milissegundosInicial;

        // Converter a diferença em milissegundos para dias
        return TimeUnit.MILLISECONDS.toDays(diferencaMilissegundos);
    }

}
