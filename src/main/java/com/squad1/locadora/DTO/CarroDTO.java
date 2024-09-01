package com.squad1.locadora.DTO;

import com.squad1.locadora.DTO.AcessorioDTO;
import com.squad1.locadora.DTO.AluguelDTO;
import java.util.Set;

import com.squad1.locadora.entities.carro.Acessorio;
import com.squad1.locadora.entities.carro.Carro;
import com.squad1.locadora.entities.carro.ModeloCarro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarroDTO {

    private Long id;
    private String placa;
    private String chassi;
    private String cor;
    private float valorDiaria;
    private Set<AcessorioDTO> acessorios; // DTO para Acessório
    private Set<AluguelDTO> aluguel; // DTO para Aluguel


    public CarroDTO(Long id, String placa, ModeloCarro modeloCarro, String cor, Set<Acessorio> acessorios) {
    }
}
