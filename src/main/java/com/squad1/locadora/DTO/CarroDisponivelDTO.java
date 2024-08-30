package com.squad1.locadora.DTO;

import com.squad1.locadora.entities.carro.Acessorio;
import com.squad1.locadora.entities.carro.ModeloCarro;
import java.util.Set;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CarroDisponivelDTO {

    private Long id;
    private float valorDiaria;
    private ModeloCarro modeloCarro;
    private Set<Acessorio> acessorios;
    private boolean reserva;

    //Constructor Explicito pois tava dando um erro com o @AllArgsConstructos do lombok
    public CarroDisponivelDTO(Long id, float valorDiaria, ModeloCarro modeloCarro, Set<Acessorio> acessorios, boolean reserva) {
        this.id = id;
        this.valorDiaria = valorDiaria;
        this.modeloCarro = modeloCarro;
        this.acessorios = acessorios;
        this.reserva = reserva;
    }

}
